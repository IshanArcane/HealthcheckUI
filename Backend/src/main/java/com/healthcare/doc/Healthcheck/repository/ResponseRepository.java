package com.healthcare.doc.Healthcheck.repository;


import com.healthcare.doc.Healthcheck.model.Response;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends MongoRepository<Response, String> {
    List<Response> findByPatientId(String patientId);
}