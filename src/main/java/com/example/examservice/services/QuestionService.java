package com.example.examservice.services;

import com.example.examservice.dto.QuestionDTO;
import com.example.examservice.entities.Question;
import com.example.examservice.repositories.QuestionRepository;

public class QuestionService {
    private QuestionRepository questionRepository;
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    public void saveQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        for (Question question1 : questionDTO.getQuestions())
        {
            question.setQuestion(question1.getQuestion());
            question.setAnswer(question1.getAnswer());
            question.setType(question1.getType());
            question.setExamID(question1.getExamID());
            questionRepository.save(question);
        }
    }
}
