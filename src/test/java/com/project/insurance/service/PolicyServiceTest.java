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
    void testGetAll() {
        List<Policy> policyList = List.of(samplePolicy);
        when(policyRepo.findAll()).thenReturn(policyList);

        List<Policy> result = policyService.getPolicyAll();
        assertEquals(1, result.size());
        verify(policyRepo, times(1)).findAll();

    }

    @Test
    void testGetPolicyById() {
        when(policyRepo.findById(1L)).thenReturn(Optional.of(samplePolicy));
        Policy policy = policyService.getPolicyById(1L);
        assertNotNull(policy);
        assertEquals("Touhid",policy.getHolderName());
        verify(policyRepo,times(1)).findById(1L);
    }


}
