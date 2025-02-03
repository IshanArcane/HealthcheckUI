package com.healthcare.doc.Healthcheck.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.doc.Healthcheck.model.Admin;
import com.healthcare.doc.Healthcheck.model.DTOs.DoctorProfileDTO;
import com.healthcare.doc.Healthcheck.model.DTOs.PatientProfileDTO;
import com.healthcare.doc.Healthcheck.model.Doctor;
import com.healthcare.doc.Healthcheck.model.Patient;
import com.healthcare.doc.Healthcheck.model.User;
import com.healthcare.doc.Healthcheck.repository.AdminRepository;
import com.healthcare.doc.Healthcheck.repository.DoctorRepository;
import com.healthcare.doc.Healthcheck.repository.PatientRepository;
import com.healthcare.doc.Healthcheck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    // Register user with role
    public void registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        userRepository.save(user); // Save in the user collection

        switch (user.getRole().toLowerCase()) {
            case "doctor":
                Doctor doctor = new Doctor();
                doctor.setUsername(user.getUsername());
                doctor.setPassword(user.getPassword());
                doctor.setEmail(user.getEmail());
                doctor.setRole(user.getRole());
                doctorRepository.save(doctor); // Save in the doctor collection
                break;

            case "patient":
                Patient patient = new Patient();
                patient.setUsername(user.getUsername());
                patient.setPassword(user.getPassword());
                patient.setRole(user.getRole());
                patient.setEmail(user.getEmail());
                patientRepository.save(patient); // Save in the patient collection
                break;

            case "admin":
                Admin admin = new Admin();
                admin.setUsername(user.getUsername());
                admin.setPassword(user.getPassword());
                admin.setRole(user.getRole());
                admin.setEmail(user.getEmail());
                adminRepository.save(admin); // Save in the admin collection
                break;

            default:
                throw new IllegalArgumentException("Invalid role specified!");
        }
    }

    // Authenticate user with role-based logic
    public User authenticateUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        User user = optionalUser.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }

        // Return user based on role
        switch (user.getRole().toLowerCase()) {
            case "doctor":
                return doctorRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Doctor not found!"));
            case "patient":
                return patientRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Patient not found!"));
            case "admin":
                return adminRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Admin not found!"));
            default:
                throw new RuntimeException("Invalid role!");
        }
    }
    public void updateUserProfile(String username, Object userProfile) {
        // Retrieve the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Convert the userProfile to the correct DTO based on the role
        if (user.getRole().equalsIgnoreCase("DOCTOR")) {
            DoctorProfileDTO doctorProfileDTO = objectMapper.convertValue(userProfile, DoctorProfileDTO.class);
            Doctor doctor = doctorRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Doctor not found with username: " + username));

            // Update doctor details
            doctor.setFirstName(doctorProfileDTO.getFirstName());
            doctor.setLastName(doctorProfileDTO.getLastName());
            doctor.setContactNumber(doctorProfileDTO.getContactNumber());
            doctor.setSpecialization(doctorProfileDTO.getSpecialization());
            doctor.setQualifications(doctorProfileDTO.getQualifications());
            doctor.setExperience(doctorProfileDTO.getExperience());
            doctor.setAvailability(doctorProfileDTO.getAvailability());
            doctor.setPhotoUrl(doctorProfileDTO.getPhotoUrl());
            doctor.setEmail(doctorProfileDTO.getEmail());

            doctorRepository.save(doctor);

        } else if (user.getRole().equalsIgnoreCase("PATIENT")) {
            PatientProfileDTO patientProfileDTO = objectMapper.convertValue(userProfile, PatientProfileDTO.class);
            Patient patient = patientRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Patient not found with username: " + username));

            // Update patient details
            patient.setFirstName(patientProfileDTO.getFirstName());
            patient.setLastName(patientProfileDTO.getLastName());
            patient.setPhoneNumber(patientProfileDTO.getPhoneNumber());
            patient.setAge(patientProfileDTO.getAge());
            patient.setEmail(patientProfileDTO.getEmail());
            patient.setAddress(patientProfileDTO.getAddress());
            patient.setGender(patientProfileDTO.getGender());
            patient.setDateOfBirth(patientProfileDTO.getDateOfBirth());
            patient.setMedicalHistory(patientProfileDTO.getMedicalHistory());

            patientRepository.save(patient);

        } else {
            throw new IllegalArgumentException("Unsupported role: " + user.getRole());
        }
    }
}
