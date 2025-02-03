package com.healthcare.doc.Healthcheck.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.healthcare.doc.Healthcheck.repository.PatientRepository;
import com.healthcare.doc.Healthcheck.repository.DoctorRepository;
import com.healthcare.doc.Healthcheck.model.Patient;
import com.healthcare.doc.Healthcheck.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    public EmailService(JavaMailSender mailSender, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.mailSender = mailSender;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public void sendAppointmentStatusEmail(String patientId, String doctorId, String appointmentDate, boolean isApproved) {
        // Retrieve patient and doctor details
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found for ID: " + patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found for ID: " + doctorId));

        String patientEmail = patient.getEmail();
        String patientName = patient.getFirstName(); // Assuming patient has a first name
        String doctorName = doctor.getFirstName() + " " + doctor.getLastName(); // Assuming doctor has first and last name

        // Validate patient's email
        if (patientEmail == null || patientEmail.isEmpty()) {
            logger.error("Patient email is null or empty.");
            throw new IllegalArgumentException("Patient email cannot be null or empty");
        }

        String subject;
        String body;

        if (isApproved) {
            subject = "Appointment Confirmed";
            body = "Dear " + patientName + ",\n\n"
                    + "Your appointment with Dr. " + doctorName + " on " + appointmentDate + " has been confirmed.\n\n"
                    + "Thank you for choosing our healthcare services.\n\n"
                    + "Best regards,\nHealthcare Team";
        } else {
            subject = "Appointment Rejected";
            body = "Dear " + patientName + ",\n\n"
                    + "We regret to inform you that your appointment with Dr. " + doctorName + " on " + appointmentDate
                    + " has been rejected.\n\n"
                    + "Please try booking another appointment at a different time.\n\n"
                    + "Best regards,\nHealthcare Team";
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(patientEmail);
        message.setSubject(subject);
        message.setText(body);

        try {
            logger.info("Sending email to: {}", patientEmail);
            mailSender.send(message);
            logger.info("Email sent successfully to: {}", patientEmail);
        } catch (Exception e) {
            logger.error("Error sending email to {}: {}", patientEmail, e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
