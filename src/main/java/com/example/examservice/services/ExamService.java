package com.example.examservice.services;

import com.example.examservice.entities.Exam;
import com.example.examservice.repositories.ExamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
