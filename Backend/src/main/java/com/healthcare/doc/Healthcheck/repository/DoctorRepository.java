package com.healthcare.doc.Healthcheck.repository;

import com.healthcare.doc.Healthcheck.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
    Optional<Doctor> findByUsername(String username);

        List<Doctor> findBySpecialization(String specialization);

        List<Doctor> findBySpecializationAndLocation(String specialization, String location);
    }


