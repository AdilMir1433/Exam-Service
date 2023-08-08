package com.example.examservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
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
    @Column(unique = true)
    String examTitle;

    @DateTimeFormat(pattern = "HH:mm") // Specify the date format you are using for startTime and endTime
    @Temporal(TemporalType.TIME)
    Date startTime;

    @DateTimeFormat(pattern = "HH:mm") // Specify the date format you are using for startTime and endTime
    @Temporal(TemporalType.TIME)
    Date endTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd") // Specify the date format you are using for startDate
    @Temporal(TemporalType.DATE)
    Date startDate;

    Long subjectID;
    Long teacherID;
    boolean isApproved;
}
