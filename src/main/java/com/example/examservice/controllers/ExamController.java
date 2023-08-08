package com.example.examservice.controllers;

import com.common.QuestionDTO;
import com.example.examservice.entities.Exam;
import com.example.examservice.entities.Question;
import com.example.examservice.entities.Subject;
import com.example.examservice.services.ExamService;
import com.example.examservice.services.QuestionService;
import com.example.examservice.services.SubjectService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
@Slf4j
public class ExamController {

    private final ExamService examService;
    private final SubjectService subjectService;
    private final QuestionService questionService;

    @GetMapping("/save-exam")
    public String getExam() {
        return "Exam";
    }

    @PostMapping("/create-subject")
    public String createSubject(@RequestParam String subjectName) {
        log.info("Subject Received Name : {} ", subjectName);
        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        subjectService.saveSubject(subject);
        log.info("Subject saved successfully");
        return "redirect:/ui/welcome";
    }

    @GetMapping("/checks")
    public String check() {
        return "Exam";
    }

    @GetMapping("/get-all-subjects")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/save-exam")
    public String saveExam(@RequestBody Exam exam) {
        log.info("Exam Received : {} ", exam);
        exam.setApproved(false);
        examService.saveExam(exam);
        log.info("Exam saved successfully");
        return "redirect:/ui/create-questions";
    }
    @PostMapping("/save-question")
    public String saveQuestion(@RequestBody QuestionDTO questionDTO) {
        log.info("Question : {} ", questionDTO.getQuestion() + " " + questionDTO.getOption1() + " " + questionDTO.getOption2() + " " + questionDTO.getOption3() + " " + questionDTO.getAnswer());
        Question question = new Question();
        question.setQuestion(questionDTO.getQuestion());
        question.setOption1(questionDTO.getOption1());
        question.setOption2(questionDTO.getOption2());
        question.setOption3(questionDTO.getOption3());
        question.setAnswer(questionDTO.getAnswer());
        question.setExamID(questionDTO.getExamID());
        question.setType(questionDTO.getQuestionType());
        questionService.saveQuestion(question);
        log.info("Exam saved successfully");
        return "redirect:/ui/create-questions";
    }
    @GetMapping("/get-exam")
    public Long getExam(@RequestParam String examName) {
        log.info("Subject Received Name : {} ", examName);
        return examService.getExamIdByExamTitle(examName);
    }

}
