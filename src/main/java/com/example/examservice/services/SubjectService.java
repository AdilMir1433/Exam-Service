package com.example.examservice.services;

import com.example.examservice.entities.Subject;
import com.example.examservice.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private SubjectRepository subjectRepository;
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    public void saveSubject(Subject subject) {
        subjectRepository.save(subject);
    }
    private Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).get();
    }
    public void deleteSubjectById(Long id) {
        subjectRepository.deleteById(id);
    }
    public void getSubjectByName(String name) {
        subjectRepository.findBySubjectName(name);
    }
    public List<Subject> getAllSubjects() {
       return subjectRepository.findAll();
    }
}
