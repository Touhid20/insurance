package com.project.insurance.repo;

import com.project.insurance.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepo extends JpaRepository<Policy, Long> {
}
