package com.healthcare.doc.Healthcheck.controller;


import com.healthcare.doc.Healthcheck.model.DTOs.ResponseDTO;
import com.healthcare.doc.Healthcheck.model.Question;
import com.healthcare.doc.Healthcheck.model.Response;
import com.healthcare.doc.Healthcheck.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@PreAuthorize("hasRole('PATIENT')")
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private ResponseService responseService;

    // Get all questions
    @GetMapping("/questions")
    public Iterable<Question> getAllQuestions() {
        return responseService.getAllQuestions();
    }

    // Submit responses
//    @PostMapping("/responses")
//    public Response submitResponse(@RequestBody ResponseDTO responseDTO) {
//        return responseService.submitResponse(responseDTO);
//    }
//
    @PostMapping("/responses")
    public Response submitResponse(@RequestBody ResponseDTO responseDTO) {
        String patientId = SecurityContextHolder.getContext().getAuthentication().getName(); // Get patient ID from token
        responseDTO.setPatientId(patientId); // Automatically set patient ID
        return responseService.submitResponse(responseDTO);
    }


    // Get responses by patient ID
    @GetMapping("/responses/{patientId}")
    public List<Response> getResponsesByPatientId(@PathVariable String patientId) {
        return responseService.getResponsesByPatientId(patientId);
    }
}
