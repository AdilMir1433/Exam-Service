package com.example.examservice.repositories;

import com.example.examservice.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    public Subject findBySubjectName(String name);
    public List<Subject> findAll();
}
