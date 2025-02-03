package com.healthcare.doc.Healthcheck.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id; // Unique appointment ID
    private String patientId; // Patient's ID
    private String doctorId; // Doctor's ID
    private LocalDate appointmentDate; // Scheduled date
    private String status; // Status: "Pending", "Confirmed", "Cancelled"

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

    public Appointment() {
        // Default constructor
    }

    public Appointment(String patientId, String doctorId, LocalDate appointmentDate, String status) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }
}
