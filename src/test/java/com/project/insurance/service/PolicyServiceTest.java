package com.project.insurance.service;

import com.project.insurance.entity.Policy;
import com.project.insurance.repo.PolicyRepo;
import com.project.insurance.service.impl.PolicyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PolicyServiceTest {

    @Mock
    private PolicyRepo policyRepo;

    @InjectMocks
    private PolicyServiceImpl policyService;

    private Policy samplePolicy;

    @BeforeEach
    void setUP() {
        samplePolicy = new Policy();
        samplePolicy.setId(1L);
        samplePolicy.setHolderName("Touhid");
        samplePolicy.setType("Life Insurance");
        samplePolicy.setStartDate(LocalDate.now());
        samplePolicy.setEndDate(LocalDate.now().plusYears(1));
        samplePolicy.setPremiumAmount(1000.00);
    }

    @Test
    void testGetPolicyAll() {
        List<Policy> policyList = List.of(samplePolicy);
        when(policyRepo.findAll()).thenReturn(policyList);

        List<Policy> result = policyService.getPolicyAll();
        assertEquals(1, result.size());
        verify(policyRepo, times(1)).findAll();
        System.out.println("testGetPolicyAll is complete");

    }

    @Test
    void testGetPolicyById() {
        when(policyRepo.findById(1L)).thenReturn(Optional.of(samplePolicy));

        Policy policy = policyService.getPolicyById(1L);
        assertNotNull(policy);
        assertEquals("Touhid", policy.getHolderName());
        verify(policyRepo, times(1)).findById(1L);

        System.out.println("testGetPolicyById is complete");
    }

    @Test
    void testGetPolicyByIdNotFound() {
        when(policyRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            policyService.getPolicyById(1L);
        });
        System.out.println("testGetPolicyByIdNotFound is complete");
    }

    @Test
    void testCreatePolicy() {
        when(policyRepo.save(samplePolicy)).thenReturn(samplePolicy);

        Policy result = policyService.create(samplePolicy);
        assertEquals("Touhid", result.getHolderName());
        verify(policyRepo).save(samplePolicy);

        System.out.println("testCreatePolicy is complete");
    }

    @Test
    void testUpdatePolicy() {
        Policy updatePolicy = new Policy();
        updatePolicy.setHolderName("Ashik");
        updatePolicy.setType("Health");
        updatePolicy.setStartDate(LocalDate.now());
        updatePolicy.setEndDate(LocalDate.now().plusYears(2));
        updatePolicy.setPremiumAmount(2000.00);

        when(policyRepo.findById(1L)).thenReturn(Optional.of(samplePolicy));
        when(policyRepo.save(any(Policy.class))).thenReturn(updatePolicy);

        Policy result = policyService.update(1L,updatePolicy);
        assertEquals("Ashik",result.getHolderName());
        verify(policyRepo).save(any(Policy.class));
        System.out.println("testUpdatePolicy is complete");
    }

}
