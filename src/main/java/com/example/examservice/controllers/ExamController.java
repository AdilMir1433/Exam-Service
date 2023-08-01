package com.example.examservice.controllers;

import com.example.examservice.entities.Subject;
import com.example.examservice.services.ExamService;
import com.example.examservice.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/exam")
@AllArgsConstructor
public class ExamController {

    WebClient.Builder webClientBuilder;
    ExamService examService;
    SubjectService subjectService;

    @GetMapping("/save-exam")
    public String getExam() {
        String userServiceBaseUrl = "http://localhost:8080/user-service";
        String htmlPageEndpoint = "/home/get-teacher-id";
        String url = userServiceBaseUrl + htmlPageEndpoint;
        Long teachID =  webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
        return "Exam";
    }
    @PostMapping("/create-subject")
    public void createSubject(Subject subject) {
        subjectService.saveSubject(subject);
    }
    @GetMapping("/checks")
    public String check() {
        return "Exam";
    }
}
