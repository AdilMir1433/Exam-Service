package com.example.examservice.services;

import com.common.ExamDTOs.ExamDTO;
import com.common.ExamDTOs.ExamObject;
import com.common.Exception.InternalException;
import com.common.Exception.NoSuchElementException;
import com.common.Exception.ResourceNotFoundException;
import com.example.examservice.entities.Exam;
import com.example.examservice.repositories.ExamRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamService {
    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;


    /** Method to save an exam into database */

    @Transactional
    public void saveExam(Exam exam){
        try{
            examRepository.save(exam);
        }
        catch (Exception e)
        {
            throw new InternalException("Could not save exam");
        }

    }


    /** Method to get exam by its title */
    public Long getExamIdByExamTitle(String examTitle)
    {
        try{
            return  examRepository.findExamByExamTitle(examTitle).getId();
        }
        catch (Exception e)
        {
            throw new NoSuchElementException("Exam with Name : " + examTitle + ". Not found");
        }

    }

    /** Method to get all exams from database */
    public List<Exam> getAllExams(){
        try{
            return examRepository.findAll();
        }
        catch (Exception e)
        {
            throw new InternalException("Problem Fetching Exams");
        }
    }

    /** Method to get all exams from database and convert them into examDto */
    public List<ExamObject> convertedExams() {
        try {
            List<Exam> exams = examRepository.findAll();
            List<ExamObject> examDTOS = new ArrayList<>();
            for (Exam exam : exams) {
                ExamObject examObject = modelMapper.map(exam, ExamObject.class);
                examDTOS.add(examObject);
            }
            return examDTOS;
        } catch (Exception e) {
            throw new InternalException("Exam not found");
        }
    }

    /** Method to get exam by its id */
    public Exam getExamById(Long id) {
        try {
            return examRepository.findById(id).get();
        } catch (Exception e) {
            throw new NoSuchElementException("Exam with ID : " + id + ". Not found");
        }
    }

    /** Method to get exam by its id and convert it into examDto */
    public List<ExamDTO> examLists()
    {
        try {
            List<Exam> exams = examRepository.findAll();
            List<ExamDTO> examDTOS = new ArrayList<>();
            for (Exam exam : exams) {
                ExamDTO examDTO = new ExamDTO();
                examDTO.setApproved(exam.isApproved());
                examDTO.setExamTitle(exam.getExamTitle());
                examDTO.setSubjectID(exam.getSubjectID());
                examDTO.setTeacherID(exam.getTeacherID());
                examDTO.setId(exam.getId());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startDateString = dateFormat.format(exam.getStartDate());
                examDTO.setStartDate(startDateString);

                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                String startTimeString = timeFormat.format(exam.getStartTime());
                examDTO.setStartTime(startTimeString);

                SimpleDateFormat endTimeFormat = new SimpleDateFormat("HH:mm");
                String endTimeString = timeFormat.format(exam.getEndTime());
                examDTO.setStartTime(endTimeString);

                examDTOS.add(examDTO);
            }
            return examDTOS;
        }
        catch (Exception e)
        {
            throw new InternalException("No exams found");
        }
    }
}
