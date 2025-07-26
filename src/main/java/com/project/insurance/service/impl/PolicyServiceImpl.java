package com.project.insurance.service.impl;

import com.project.insurance.entity.Policy;
import com.project.insurance.repo.PolicyRepo;
import com.project.insurance.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {
    @Autowired
    private final PolicyRepo policyRepo;

    public PolicyServiceImpl(PolicyRepo policyRepo) {
        this.policyRepo = policyRepo;
    }

    @Override
    public List<Policy> getPolicyAll() {
        return policyRepo.findAll();
    }

    @Override
    public Policy getPolicyById(Long id) {
        return policyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Policy not found with id: " + id));
    }

    @Override
    public Policy create(Policy policy) {
        return policyRepo.save(policy);
    }

    @Override
    public Policy update(Long id, Policy update) {
        Policy existing = getPolicyById(id);
        existing.setHolderName(update.getHolderName());
        existing.setType(update.getType());
        existing.setStartDate(update.getStartDate());
        existing.setEndDate(update.getEndDate());
        existing.setPremiumAmount(update.getPremiumAmount());
        return policyRepo.save(existing);
    }


}
