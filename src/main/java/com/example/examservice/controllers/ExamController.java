package com.example.examservice.controllers;

import com.common.ExamDTO;
import com.common.ExamObject;
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

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @GetMapping("/get-all-subjects")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/save-exam")
    public String saveExam(@RequestBody com.common.Exam exam) {
        Exam exam1 = new Exam();
        exam1.setExamTitle(exam.getExamTitle());
        exam1.setSubjectID(exam.getSubjectID());
        exam1.setTeacherID(exam.getTeacherID());
        exam1.setStartDate(exam.getStartDate());
        exam1.setStartTime(exam.getStartTime());
        exam1.setEndTime(exam.getEndTime());
        log.info("Exam Received : {} ", exam);
        exam1.setApproved(false);
        examService.saveExam(exam1);
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
        question.setQuestionScore(questionDTO.getQuestionScore());
        questionService.saveQuestion(question);
        log.info("Exam saved successfully");
        return "redirect:/ui/create-questions";
    }
    @GetMapping("/get-exam")
    public Long getExam(@RequestParam String examName) {
        log.info("Subject Received Name : {} ", examName);
        return examService.getExamIdByExamTitle(examName);
    }
    @GetMapping("/get-all-exams")
    public List<ExamObject> getAllExams() {
        return examService.convertedExams();
    }

    @PostMapping("/approve-exam")
    public String approveExam(@RequestParam Long examId) {
        log.info("Exam Received Name : {} ", examId);
        Exam exam = examService.getExamById(examId);
        exam.setApproved(true);
        examService.saveExam(exam);
        return "redirect:/ui/welcome";
    }
    @PostMapping("/unapprove-exam")
    String unapproveExam(@RequestParam Long examId){
        log.info("Exam Received Name : {}select* from exam; ", examId);
        Exam exam = examService.getExamById(examId);
        exam.setApproved(false);
        examService.saveExam(exam);
        return "redirect:/ui/welcome";
    }

    @PostMapping("/check-if-valid")
    public boolean checkIfValid(@RequestParam Long examId) {
        log.info("Exam Received Name : {} ", examId);
        Exam exam = examService.getExamById(examId);
        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());
        if (exam.getEndTime().before(date)) {
            return false;
        }
        else if(exam.getStartDate().before(date)) {
            return false;
        }
        else if(exam.getStartTime().after(time)) {
            return false;
        }
        else if(exam.getEndTime().before(time)) {
            return false;
        }
        else {
            return true;
        }
    }
    @GetMapping("/get-exam-list")
    public List<ExamDTO> getExamDTOList() {
        return examService.examLists();
    }
    @GetMapping("/get-questions-by-id")
    public List<QuestionDTO> getExamById(@RequestParam Long examId) {
        return questionService.getAllQuestionsByExamID(examId);
    }

    @GetMapping("/get-exam-by-id")
    public ExamDTO getSingleExamById(@RequestParam Long examId) {
        Exam exam = examService.getExamById(examId);
        ExamDTO examDTO = new ExamDTO();
        examDTO.setExamTitle(exam.getExamTitle());
        examDTO.setSubjectID(exam.getSubjectID());
        examDTO.setTeacherID(exam.getTeacherID());
        examDTO.setStartDate(String.valueOf(exam.getStartDate()));
        examDTO.setStartTime(String.valueOf(exam.getStartTime()));
        examDTO.setEndTime(String.valueOf(exam.getEndTime()));
        examDTO.setApproved(exam.isApproved());
        examDTO.setId(exam.getId());
        return examDTO;
    }
}
