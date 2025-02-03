package com.healthcare.doc.Healthcheck.security;

import com.healthcare.doc.Healthcheck.model.Admin;
import com.healthcare.doc.Healthcheck.model.Doctor;
import com.healthcare.doc.Healthcheck.model.Patient;
import com.healthcare.doc.Healthcheck.repository.DoctorRepository;
import com.healthcare.doc.Healthcheck.repository.PatientRepository;
import com.healthcare.doc.Healthcheck.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Attempt to find the user in the Patient repository
        Optional<Patient> patientOpt = patientRepository.findByUsername(username);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("patient")); // Add the appropriate role
            return new org.springframework.security.core.userdetails.User(patient.getUsername(), patient.getPassword(), authorities);
        }

        // Attempt to find the user in the Doctor repository
        Optional<Doctor> doctorOpt = doctorRepository.findByUsername(username);
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("doctor")); // Add the appropriate role
            return new org.springframework.security.core.userdetails.User(doctor.getUsername(), doctor.getPassword(), authorities);
        }

        // Attempt to find the user in the Admin repository
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("admin")); // Add the appropriate role
            return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), authorities);
        }

        // If no user is found in any repository, throw an exception
        throw new UsernameNotFoundException("User not found");
    }
}
