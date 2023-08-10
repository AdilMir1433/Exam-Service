package com.example.examservice.controllers;

import com.common.ExamDTOs.ExamDTO;
import com.common.ExamDTOs.ExamObject;
import com.common.QuestionDTOs.QuestionDTO;
import com.example.examservice.entities.Exam;
import com.example.examservice.entities.Question;
import com.example.examservice.entities.Subject;
import com.example.examservice.services.ExamService;
import com.example.examservice.services.QuestionService;
import com.example.examservice.services.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
@Slf4j


/** Controller to expose All endpoints of exam service so that Front end microservice can use it. */

public class ExamController {

    private final ExamService examService;
    private final SubjectService subjectService;
    private final QuestionService questionService;
    private final ModelMapper modelMapper;
    /** Method to save a newly generated subject into database */

    @PostMapping("/create-subject")
    public String createSubject(@RequestParam String subjectName) {
        log.info("Subject Received Name : {} ", subjectName);
        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        subjectService.saveSubject(subject);
        log.info("Subject saved successfully");
        return "redirect:/ui/welcome";
    }

    /** Method to get all subjects from database */
    @GetMapping("/get-all-subjects")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }



    /** Method to save a newly created exam into database */
    @PostMapping("/save-exam")
    public String saveExam(@RequestBody com.common.ExamDTOs.Exam exam) {
        Exam createdExam =  modelMapper.map(exam, Exam.class);
        examService.saveExam(createdExam);
        log.info("Exam saved successfully");
        return "redirect:/ui/create-questions";
    }


    /** Method to save a question into database */
    @PostMapping("/save-question")
    public String saveQuestion(@RequestBody QuestionDTO questionDTO) {
        log.info("Question : {} ", questionDTO.getQuestion() + " " + questionDTO.getOption1() + " " + questionDTO.getOption2() + " " + questionDTO.getOption3() + " " + questionDTO.getAnswer());
        Question question = modelMapper.map(questionDTO, Question.class);
        questionService.saveQuestion(question);
        log.info("Exam saved successfully");
        return "redirect:/ui/create-questions";
    }


    /** Method to get an exam from database using its title */
    @GetMapping("/get-exam")
    public Long getExam(@RequestParam String examName) {
        log.info("Subject Received Name : {} ", examName);
        return examService.getExamIdByExamTitle(examName);
    }

    /** Method to get all exams from database */
    @GetMapping("/get-all-exams")
    public List<ExamObject> getAllExams() {
        return examService.convertedExams();
    }



    /** Method to change the state of an exam from unapproved to approved */
    @PostMapping("/approve-exam")
    public String approveExam(@RequestParam Long examId) {
        log.info("Exam Received Name : {} ", examId);
        Exam exam = examService.getExamById(examId);
        exam.setApproved(true);
        examService.saveExam(exam);
        return "redirect:/ui/welcome";
    }

    /** Method to change the state of an exam from approved to unapproved */
    @PostMapping("/unapprove-exam")
    String unapproveExam(@RequestParam Long examId){
        log.info("Exam Received Name : {}select* from exam; ", examId);
        Exam exam = examService.getExamById(examId);
        exam.setApproved(false);
        examService.saveExam(exam);
        return "redirect:/ui/welcome";
    }

    /** Method to get all exams from database */
    @GetMapping("/get-exam-list")
    public List<ExamDTO> getExamDTOList() {
        return examService.examLists();
    }

    /** Method to get all questions of a particular exam from database */
    @GetMapping("/get-questions-by-id")
    public List<QuestionDTO> getExamById(@RequestParam Long examId) {
        return questionService.getAllQuestionsByExamID(examId);
    }


    /** Method to retrieve a particular exam from database */

    @GetMapping("/get-exam-by-id")
    public ExamDTO getSingleExamById(@RequestParam Long examId) {
        Exam exam = examService.getExamById(examId);
        ExamDTO examDTO = this.modelMapper.map(exam, ExamDTO.class);
        return examDTO;
    }
}
