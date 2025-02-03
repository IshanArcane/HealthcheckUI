package com.healthcare.doc.Healthcheck.service;


import com.healthcare.doc.Healthcheck.model.DTOs.DoctorProfileDTO;
import com.healthcare.doc.Healthcheck.model.Doctor;
import com.healthcare.doc.Healthcheck.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Retrieve doctor by username
    public Doctor getDoctorByUsername(String username) {
        return doctorRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Doctor with username " + username + " not found."));
    }


        public DoctorProfileDTO getDoctorProfile(String doctorId) {
            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + doctorId));
            return mapToDTO(doctor);
        }

        public List<DoctorProfileDTO> getAllDoctors() {
            List<Doctor> doctors = doctorRepository.findAll();
            return doctors.stream().map(this::mapToDTO).collect(Collectors.toList());
        }

        public DoctorProfileDTO updateDoctorProfile(String doctorId, DoctorProfileDTO doctorProfileDTO) {
            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + doctorId));

            doctor.setFirstName(doctorProfileDTO.getFirstName());
            doctor.setLastName(doctorProfileDTO.getLastName());
            doctor.setContactNumber(doctorProfileDTO.getContactNumber());
            doctor.setAvailability(doctorProfileDTO.getAvailability());
            doctor.setSpecialization(doctorProfileDTO.getSpecialization());
            doctor.setQualifications(doctorProfileDTO.getQualifications());
            doctor.setExperience(doctorProfileDTO.getExperience());
            doctor.setPhotoUrl(doctorProfileDTO.getPhotoUrl());
            doctor.setLocation(doctorProfileDTO.getLocation());
            doctor.setRatings(doctorProfileDTO.getRatings());

            doctorRepository.save(doctor);

            return mapToDTO(doctor);
        }


    private DoctorProfileDTO mapToDTO(Doctor doctor) {
        DoctorProfileDTO dto = new DoctorProfileDTO();
        dto.setId(doctor.getId());
        dto.setFirstName(doctor.getFirstName());
        dto.setLastName(doctor.getLastName());
        dto.setContactNumber(doctor.getContactNumber());
        dto.setAvailability(doctor.getAvailability());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setQualifications(doctor.getQualifications());
        dto.setExperience(doctor.getExperience());
        dto.setPhotoUrl(doctor.getPhotoUrl());
        dto.setLocation(doctor.getLocation());
        dto.setRatings(doctor.getRatings());
        return dto;
    }

}



