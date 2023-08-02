package com.example.examservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Getter
@Setter

public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String examTitle;
    Time startTime;
    Time endTime;
    Date startDate;
    Long subjectID;
    Long teacherID;
    boolean isActivated;
    boolean isApproved;
}
