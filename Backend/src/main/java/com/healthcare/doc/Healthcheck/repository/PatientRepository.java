package com.healthcare.doc.Healthcheck.repository;

import com.healthcare.doc.Healthcheck.model.Doctor;
import com.healthcare.doc.Healthcheck.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    Optional<Patient> findByUsername(String username);
}

