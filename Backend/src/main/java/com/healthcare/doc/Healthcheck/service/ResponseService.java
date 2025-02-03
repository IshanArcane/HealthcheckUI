package com.healthcare.doc.Healthcheck.service;


import com.healthcare.doc.Healthcheck.model.DTOs.ResponseDTO;
import com.healthcare.doc.Healthcheck.model.Question;
import com.healthcare.doc.Healthcheck.model.Response;
import com.healthcare.doc.Healthcheck.repository.QuestionRepository;
import com.healthcare.doc.Healthcheck.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // Submit patient responses
    public Response submitResponse(ResponseDTO responseDTO) {
        Response response = new Response();
        response.setPatientId(responseDTO.getPatientId());
        response.setAnswers(responseDTO.getAnswers());
        return responseRepository.save(response);
    }

    // Get responses for a specific patient
    public List<Response> getResponsesByPatientId(String patientId) {
        return responseRepository.findByPatientId(patientId);
    }

    // Get all questions (for patients to answer)
    public Iterable<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
