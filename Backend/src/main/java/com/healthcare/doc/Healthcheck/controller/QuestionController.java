package com.healthcare.doc.Healthcheck.controller;


import com.healthcare.doc.Healthcheck.model.DTOs.QuestionDTO;
import com.healthcare.doc.Healthcheck.model.Question;
import com.healthcare.doc.Healthcheck.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/admin/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // Create a question
    @PostMapping
    public Question createQuestion(@RequestBody QuestionDTO questionDTO) {
        return questionService.createQuestion(questionDTO);
    }

    // Update a question
    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable String id, @RequestBody QuestionDTO questionDTO) {
        return questionService.updateQuestion(id, questionDTO);
    }

    // Get all questions
    @GetMapping
    public Iterable<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // Delete a question
    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
        return "Question with ID " + id + " deleted successfully";
    }
}

