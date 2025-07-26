package com.project.insurance.service;

import com.project.insurance.entity.Policy;
import jakarta.validation.Valid;

import java.util.List;

public interface PolicyService {
    List<Policy> getPolicyAll();

    Policy getPolicyById(Long id);

    Policy create(@Valid Policy policy);

    Policy update(Long id, @Valid Policy update);
}
