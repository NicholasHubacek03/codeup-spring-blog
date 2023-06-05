package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class DiceRoll {

    @GetMapping(path = "/roll-dice")
    public String mainPage() {
        return "dice";
    }

    @GetMapping("/roll-dice/{num}")
    public String sayHello(@PathVariable int num, Model model) {
        String results = null;
        if(roll(num)){
            results = "u win";
        }
        else {results = "u lost";}
        model.addAttribute("results", results);
        return "dice-results";
    }
    private boolean roll(int num){
        return randNum(1,6) == num;
    }
    private int randNum(int min, int max){
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }
}