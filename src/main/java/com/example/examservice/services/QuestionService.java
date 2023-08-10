package com.example.examservice.services;

import com.common.Exception.InternalException;
import com.common.QuestionDTOs.QuestionDTO;
import com.example.examservice.entities.Question;
import com.example.examservice.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;


    /** Method to save a question into database */
    public void saveQuestion(Question question) {
        try {
            questionRepository.save(question);
        } catch (Exception e) {
           throw new InternalException("Error while saving question");
        }
    }

    /** Method to get all questions of a particular exam from database */
    public List<QuestionDTO> getAllQuestionsByExamID(Long examID) {
        try {
            log.info("Getting all questions {}" , examID);
            List<QuestionDTO> questionDTOS = new ArrayList<>();
            List<Question> questionList = questionRepository.findAll();

            List<Question> filteredList = new ArrayList<>();
            for (Question question : questionList) {
                if (question.getExamID().equals(examID)) {
                    filteredList.add(question);
                }
            }

            for (Question questionDTO : filteredList) {
                QuestionDTO questionDTOs = new QuestionDTO();
                questionDTOs.setId(questionDTO.getId());
                questionDTOs.setQuestion(questionDTO.getQuestion());
                questionDTOs.setOption1(questionDTO.getOption1());
                questionDTOs.setOption2(questionDTO.getOption2());
                questionDTOs.setOption3(questionDTO.getOption3());
                questionDTOs.setAnswer(questionDTO.getAnswer());
                questionDTOs.setExamID(questionDTO.getExamID());
                questionDTOs.setQuestionType(questionDTO.getType());
                questionDTOs.setQuestionScore(questionDTO.getQuestionScore());
                questionDTOS.add(questionDTOs);

            }
            log.info("Questions {} ", questionDTOS.size());
            return questionDTOS;
        }
        catch (Exception e){
            throw new InternalException("Error while getting all questions");
        }
    }
}
