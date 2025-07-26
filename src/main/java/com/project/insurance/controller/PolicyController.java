package com.project.insurance.controller;

import com.project.insurance.entity.Policy;
import com.project.insurance.service.PolicyService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies/")
public class PolicyController {

    private final PolicyService service;

    public PolicyController(PolicyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Policy> getAll() {

        return service.getPolicyAll();
    }

    @GetMapping("/{id}")
    public Policy getById(@PathVariable Long id) {
        return service.getPolicyById(id);
    }

    @PostMapping
    public ResponseEntity<Policy> create(@Valid @RequestBody Policy policy){
        return new ResponseEntity<>(service.create(policy), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public Policy update(@PathVariable Long id, @Valid @RequestBody Policy policy){
        return service.update(id,policy);
    }


}
