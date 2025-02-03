package com.healthcare.doc.Healthcheck.controller;

import com.healthcare.doc.Healthcheck.model.DTOs.AppointmentDTO;
import com.healthcare.doc.Healthcheck.model.DTOs.BookingRequestDTO;
import com.healthcare.doc.Healthcheck.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<AppointmentDTO> bookAppointment(@RequestBody BookingRequestDTO bookingRequestDTO) {
        // Validate input is handled in service method
        AppointmentDTO appointmentDTO = appointmentService.bookAppointment(bookingRequestDTO);

        // Return response with the AppointmentDTO
        return ResponseEntity.ok(appointmentDTO);
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentDTO> getAppointmentsForPatient(@PathVariable String patientId) {
        return appointmentService.getAppointmentsForPatient(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentDTO> getAppointmentsForDoctor(@PathVariable String doctorId) {
        return appointmentService.getAppointmentsForDoctor(doctorId);
    }
}
