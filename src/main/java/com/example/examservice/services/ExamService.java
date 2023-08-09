package com.example.examservice.services;

import com.common.ExamDTO;
import com.common.ExamObject;
import com.common.QuestionDTO;
import com.example.examservice.entities.Exam;
import com.example.examservice.repositories.ExamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ExamService {
    private ExamRepository examRepository;

    public void saveExam(Exam exam){
     examRepository.save(exam);
    }
    public Long getExamIdByExamTitle(String examTitle){
        Long id = examRepository.findExamByExamTitle(examTitle).getId();
        log.info("Exam id : {}", id);
        return id;
    }
    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }
    public List<ExamObject> convertedExams() {
        List<Exam> exams = examRepository.findAll();
        List<ExamObject> examDTOS = new ArrayList<>();
        for (Exam exam : exams) {
            ExamObject examDTO = new ExamObject();
            examDTO.setApproved(exam.isApproved());
            examDTO.setExamTitle(exam.getExamTitle());
            examDTO.setSubjectID(exam.getSubjectID());
            examDTO.setTeacherID(exam.getTeacherID());
            examDTO.setId(exam.getId());
            examDTOS.add(examDTO);
        }
        return examDTOS;
    }
    public Exam getExamById(Long id){
        return examRepository.findById(id).get();
    }
    public List<ExamDTO> examLists()
    {
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
}
