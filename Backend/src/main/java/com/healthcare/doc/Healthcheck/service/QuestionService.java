package com.healthcare.doc.Healthcheck.service;


import com.healthcare.doc.Healthcheck.exception.ResourceNotFoundException;
import com.healthcare.doc.Healthcheck.model.DTOs.QuestionDTO;
import com.healthcare.doc.Healthcheck.model.Question;
import com.healthcare.doc.Healthcheck.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // Create a new question
    public Question createQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setText(questionDTO.getText());
        question.setOptions(questionDTO.getOptions());
        return questionRepository.save(question);
    }

    // Update an existing question
    public Question updateQuestion(String id, QuestionDTO updatedQuestionDTO) {
        Optional<Question> existingQuestionOpt = questionRepository.findById(id);
        if (existingQuestionOpt.isPresent()) {
            Question existingQuestion = existingQuestionOpt.get();
            existingQuestion.setText(updatedQuestionDTO.getText());
            existingQuestion.setOptions(updatedQuestionDTO.getOptions());
            return questionRepository.save(existingQuestion);
        } else {
            throw new ResourceNotFoundException("Question with ID " + id + " not found");
        }
    }

    // Get all questions
    public Iterable<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // Delete a question by ID
    public void deleteQuestion(String id) {
        if (questionRepository.existsById(id)) {
            questionRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Question with ID " + id + " not found");
        }
    }
}
