package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
}
