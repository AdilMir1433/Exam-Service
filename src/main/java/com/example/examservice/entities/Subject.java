package com.example.examservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Transactional
@Getter
@Setter
public class Subject {
    @Id
    private Long id;
    @Column(unique = true)
    String subjectName;
}

