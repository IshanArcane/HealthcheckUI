package com.healthcare.doc.Healthcheck.controller;


import com.healthcare.doc.Healthcheck.model.DTOs.DoctorProfileDTO;
import com.healthcare.doc.Healthcheck.service.AppointmentService;
import com.healthcare.doc.Healthcheck.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorProfileDTO> getDoctorProfile(@PathVariable String doctorId) {
        DoctorProfileDTO doctorProfile = doctorService.getDoctorProfile(doctorId);
        return ResponseEntity.ok(doctorProfile);
    }

    @GetMapping
    public ResponseEntity<List<DoctorProfileDTO>> getAllDoctors() {
        List<DoctorProfileDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorProfileDTO> updateDoctorProfile(@PathVariable String doctorId,
                                                                @RequestBody DoctorProfileDTO doctorProfileDTO) {
        DoctorProfileDTO updatedProfile = doctorService.updateDoctorProfile(doctorId, doctorProfileDTO);
        return ResponseEntity.ok(updatedProfile);
    }

    // Endpoint to approve an appointment
    @PutMapping("/appointments/{appointmentId}/approve")
    public ResponseEntity<String> approveAppointment(@PathVariable String appointmentId) {
        try {
            appointmentService.approveAppointment(appointmentId);
            return ResponseEntity.ok("Appointment approved successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to reject an appointment
    @PutMapping("/appointments/{appointmentId}/reject")
    public ResponseEntity<String> rejectAppointment(@PathVariable String appointmentId) {
        try {
            appointmentService.rejectAppointment(appointmentId);
            return ResponseEntity.ok("Appointment rejected successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
