package com.healthcare.doc.Healthcheck.service;

import com.healthcare.doc.Healthcheck.model.Appointment;
import com.healthcare.doc.Healthcheck.model.DTOs.AppointmentDTO;
import com.healthcare.doc.Healthcheck.model.DTOs.BookingRequestDTO;
import com.healthcare.doc.Healthcheck.model.Doctor;
import com.healthcare.doc.Healthcheck.model.Patient;
import com.healthcare.doc.Healthcheck.repository.AppointmentRepository;
import com.healthcare.doc.Healthcheck.repository.DoctorRepository;
import com.healthcare.doc.Healthcheck.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmailService emailService;

    public AppointmentDTO bookAppointment(BookingRequestDTO bookingRequestDTO) {
        String patientId = bookingRequestDTO.getPatientId();
        String doctorId = bookingRequestDTO.getDoctorId();
        LocalDate appointmentDate = bookingRequestDTO.getAppointmentDate();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = new Appointment(patientId, doctorId, appointmentDate, "Pending");
        appointment = appointmentRepository.save(appointment);

        // No email is sent at this stage

        return new AppointmentDTO(
                appointment.getId(),
                patientId,
                doctorId,
                doctor.getFirstName() + " " + doctor.getLastName(),
                doctor.getSpecialization(),
                appointmentDate,
                appointment.getStatus()
        );
    }

    public void approveAppointment(String appointmentId) {
        updateAppointmentStatus(appointmentId, "Confirmed", true);
    }

    public void rejectAppointment(String appointmentId) {
        updateAppointmentStatus(appointmentId, "Cancelled", false);
    }

    private void updateAppointmentStatus(String appointmentId, String newStatus, boolean isApproved) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));

        if (!"Pending".equals(appointment.getStatus())) {
            throw new RuntimeException("Only pending appointments can be updated.");
        }

        appointment.setStatus(newStatus);
        appointmentRepository.save(appointment);

        // Send email notification to the patient after approval/rejection
        emailService.sendAppointmentStatusEmail(
                appointment.getPatientId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDate().toString(),
                isApproved
        );
    }
    public List<AppointmentDTO> getAppointmentsForPatient(String patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return appointments.stream().map(appointment -> new AppointmentDTO(
                appointment.getId(),
                appointment.getPatientId(),
                appointment.getDoctorId(),
                doctorRepository.findById(appointment.getDoctorId())
                        .map(doc -> doc.getFirstName() + " " + doc.getLastName())
                        .orElse("Unknown Doctor"),
                doctorRepository.findById(appointment.getDoctorId())
                        .map(Doctor::getSpecialization)
                        .orElse("Unknown Specialization"),
                appointment.getAppointmentDate(),
                appointment.getStatus()
        )).collect(Collectors.toList());
    }

    public List<AppointmentDTO> getAppointmentsForDoctor(String doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
        return appointments.stream().map(appointment -> new AppointmentDTO(
                appointment.getId(),
                appointment.getPatientId(),
                appointment.getDoctorId(),
                doctorRepository.findById(appointment.getDoctorId())
                        .map(doc -> doc.getFirstName() + " " + doc.getLastName())
                        .orElse("Unknown Doctor"),
                doctorRepository.findById(appointment.getDoctorId())
                        .map(Doctor::getSpecialization)
                        .orElse("Unknown Specialization"),
                appointment.getAppointmentDate(),
                appointment.getStatus()
        )).collect(Collectors.toList());
    }

}
