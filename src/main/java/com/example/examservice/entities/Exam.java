package com.example.examservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Transactional

public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String examTitle;
    Date startDate;
    Date endDate;
    String examDuration;
    Long subjectID;
    Long teacherID;
    boolean isActivated;
    boolean isApproved;
}
