package com.healthcare.doc.Healthcheck.controller;

import com.healthcare.doc.Healthcheck.model.Admin;
import com.healthcare.doc.Healthcheck.repository.AdminRepository;
import com.healthcare.doc.Healthcheck.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/users")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminRepository.findAll());
    }

}

