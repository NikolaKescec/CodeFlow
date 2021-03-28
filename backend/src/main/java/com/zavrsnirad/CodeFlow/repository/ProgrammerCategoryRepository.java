package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.ProgrammerCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammerCategoryRepository extends JpaRepository<ProgrammerCategory, Long> {
}
