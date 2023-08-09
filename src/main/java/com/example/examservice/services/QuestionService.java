package com.example.examservice.services;

import com.common.QuestionDTO;
import com.example.examservice.entities.Question;
import com.example.examservice.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }
    public List<QuestionDTO> getAllQuestionsByExamID(Long examID){
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        List<Question> questionList = questionRepository.findAll();

        List<Question> filteredList = new ArrayList<>();
        for (Question question : questionList) {
            if (question.getExamID().equals(examID)) {
                filteredList.add(question);
            }
        }

        for (Question questionDTO : filteredList) {
            QuestionDTO questionDTO1 = new QuestionDTO();
            questionDTO1.setQuestion(questionDTO.getQuestion());
            questionDTO1.setOption1(questionDTO.getOption1());
            questionDTO1.setOption2(questionDTO.getOption2());
            questionDTO1.setOption3(questionDTO.getOption3());
            questionDTO1.setAnswer(questionDTO.getAnswer());
            questionDTO1.setExamID(questionDTO.getExamID());
            questionDTO1.setId(questionDTO.getId());
            questionDTO1.setQuestionType(questionDTO.getType());
            questionDTO1.setQuestionScore(questionDTO.getQuestionScore());
            questionDTOS.add(questionDTO1);
        }
        return questionDTOS;
    }
}
