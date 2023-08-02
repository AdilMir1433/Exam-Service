package com.example.examservice.controllers;

import com.example.examservice.entities.Exam;
import com.example.examservice.entities.Subject;
import com.example.examservice.feignClient.FeignClient;
import com.example.examservice.services.ExamService;
import com.example.examservice.services.SubjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/exam")
@AllArgsConstructor
@Slf4j
public class ExamController {

    ExamService examService;
    SubjectService subjectService;
    FeignClient feignClient;

    @GetMapping("/save-exam")
    public String getExam() {
        return "Exam";
    }

    @PostMapping("/create-subject")
    public void createSubject(@RequestBody String subjectName) {
        log.info("Subject Received Name : {} ", subjectName);
        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        subjectService.saveSubject(subject);
        log.info("Subject saved successfully");
    }

    @GetMapping("/checks")
    public String check() {
        return "Exam";
    }

    @GetMapping("/hasExam")
    public String hasExam() {
        return feignClient.postExam();
    }

    @GetMapping("/get-all-subjects")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }
    @PostMapping("/save-exam")
    public void saveExam(Exam exam) {
//        Exam exam = new Exam();
//        exam.setExamTitle(examTitle);
//        exam.setStartTime(startTime);
//        exam.setEndTime(endTime);
//        exam.setStartDate(startDate);
//        exam.setSubjectID(subjectID);
//        exam.setTeacherID(teacherID);
//        exam.setActivated(false);
//        exam.setApproved(false);
        log.info("Exam Received : {} ", exam);
        examService.saveExam(exam);
        log.info("Exam saved successfully");
    }
}
