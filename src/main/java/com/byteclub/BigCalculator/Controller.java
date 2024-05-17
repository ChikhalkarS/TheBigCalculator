package com.byteclub.BigCalculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "calculator";
    }
    @PostMapping("/submit")
    public String submit(@ModelAttribute InputData inputData, Model model) {
        String userInput = inputData.getText();
        String message = "You entered: " + userInput;
        model.addAttribute("message", message);
        model.addAttribute("inputData", inputData); // Add this line to re-bind inputData
        return "calculator";
    }
    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num1") BigInteger num1,
            @RequestParam("operation") String operation,
            @RequestParam("num2") BigInteger num2,
            Model model) {

        BigInteger result = BigInteger.valueOf(0);
        switch (operation) {
            case "add":
                result = num1.add(num2);
                break;
            case "subtract":
                result = num1.subtract(num2);
                break;
            case "multiply":
                result = num1.multiply(num2);
                break;
            case "divide":
                if (num2.intValue() !=0) {
                    result = num1.divide(num2);
                } else {
                    model.addAttribute("error", "Cannot divide by zero");
                    return "calculator";
                }
                break;
            default:
                model.addAttribute("error", "Invalid operation");
                return "calculator";
        }

        model.addAttribute("result", result);
        return "calculator";
    }

    @PostMapping("/reset")
    public String reset(Model model) {
        model.addAttribute("result", null);
        model.addAttribute("error", null);
        return "calculator";
    }
}
