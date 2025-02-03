package com.healthcare.doc.Healthcheck.repository;


import com.healthcare.doc.Healthcheck.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {


}
