package com.example.examservice.controllers;

import com.example.examservice.dto.QuestionDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/exam")
public class HomeController {
    @GetMapping("/create-exam")
    public ModelAndView createExam() {
        ModelAndView modelAndView = new ModelAndView("create-exam");
        QuestionDTO questionDTO = new QuestionDTO();
        modelAndView.addObject("questionDTO", questionDTO);
        return modelAndView;
    }
}
