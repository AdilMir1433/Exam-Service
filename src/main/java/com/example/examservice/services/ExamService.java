package com.example.examservice.services;

import com.common.ExamObject;
import com.example.examservice.entities.Exam;
import com.example.examservice.repositories.ExamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ExamService {
    private ExamRepository examRepository;

    public void saveExam(Exam exam){
     examRepository.save(exam);
    }
    public Long getExamIdByExamTitle(String examTitle){
        Long id = examRepository.findExamByExamTitle(examTitle).getId();
        log.info("Exam id : {}", id);
        return id;
    }
    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }
    public List<ExamObject> convertedExams() {
        List<Exam> exams = examRepository.findAll();
        List<ExamObject> examDTOS = new ArrayList<>();
        for (Exam exam : exams) {
            ExamObject examDTO = new ExamObject();
            examDTO.setApproved(exam.isApproved());
            examDTO.setExamTitle(exam.getExamTitle());
            examDTO.setSubjectID(exam.getSubjectID());
            examDTO.setTeacherID(exam.getTeacherID());
            examDTO.setId(exam.getId());
            examDTOS.add(examDTO);
        }
        return examDTOS;
    }
}
