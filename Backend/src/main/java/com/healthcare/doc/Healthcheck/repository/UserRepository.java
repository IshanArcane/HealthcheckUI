package com.healthcare.doc.Healthcheck.repository;

import com.healthcare.doc.Healthcheck.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
    public interface UserRepository extends MongoRepository<User, String> {
        Optional<User> findByUsername(String username);
    }

