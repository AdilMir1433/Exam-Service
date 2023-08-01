package com.example.examservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/exam")
public class HomeController {
    @GetMapping("/home")
    public ModelAndView createExam() {
        ModelAndView modelAndView = new ModelAndView("create-exam");
        return modelAndView;
    }
}
