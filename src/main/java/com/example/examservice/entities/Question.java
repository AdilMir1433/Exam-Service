package com.example.examservice.entities;

import com.common.QuestionDTOs.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Getter
@Setter

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private QuestionType type;
    private Long examID;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String answer;
    private int questionScore;
}
