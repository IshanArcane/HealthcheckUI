package com.healthcare.doc.Healthcheck.model.DTOs;

import java.util.Map;

public class ResponseDTO {
    private String patientId;
    private Map<String, String> answers; // QuestionId -> Answer

    // Getters and Setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }
}