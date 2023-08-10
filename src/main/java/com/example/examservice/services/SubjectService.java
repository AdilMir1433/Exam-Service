package com.example.examservice.services;

import com.common.Exception.InternalException;
import com.common.Exception.NoSuchElementException;
import com.common.Exception.ResourceNotFoundException;
import com.example.examservice.entities.Subject;
import com.example.examservice.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;


    /** Method to save a subject into database */
    public void saveSubject(Subject subject) {
        try {
            subjectRepository.save(subject);
        }
        catch (Exception e) {
            throw new InternalException("Error while saving subject");
        }
    }

    /** Method to get a subject by id */
    private Subject getSubjectById(Long id) {
        try {
            return subjectRepository.findById(id).get();
        }
        catch (Exception e) {
            throw new NoSuchElementException("No such Subject Found");
        }
    }

    /** Method to delete a subject by id */
    public void deleteSubjectById(Long id) {
        try {
            subjectRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new NoSuchElementException("No such Subject Found");
        }

    }

    /** Method to get a subject by name */
    public void getSubjectByName(String name) {
        try {
            subjectRepository.findBySubjectName(name);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("No such Subject Found");
        }
    }

    /** Method to get all subjects */
    public List<Subject> getAllSubjects() {
        try {
            return subjectRepository.findAll();
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("No Subjects Found");
        }
    }
}
