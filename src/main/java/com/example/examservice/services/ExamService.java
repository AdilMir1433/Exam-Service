package com.example.examservice.services;

import com.example.examservice.entities.Exam;
import com.example.examservice.repositories.ExamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExamService {
    private ExamRepository examRepository;

    public void saveExam(Exam exam){
     examRepository.save(exam);
    }
}
