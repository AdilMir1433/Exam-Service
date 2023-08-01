package com.example.examservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Transactional

public class Score {

    @Id
    private Long id;
    private Long studentID;
    private Long examID;
    private int score;
}
