package com.healthcare.doc.Healthcheck.model.DTOs;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class AppointmentDTO {
    @Id
    private String id; // Appointment ID
    private String patientId; // Patient ID
    private String doctorId; // Doctor ID
    private String doctorName; // Doctor's full name
    private String specialization; // Doctor's specialization
    private LocalDate appointmentDate; // Appointment Date
    private String status; // Appointment Status

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppointmentDTO() {
        // Default constructor
    }

    public AppointmentDTO(String id, String patientId, String doctorId, String doctorName, String specialization, LocalDate appointmentDate, String status) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }
}
