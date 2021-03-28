package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProgrammerRepository extends JpaRepository<Programmer, Long> {

    Programmer findByUsername(String username);

    Programmer findByEmail(String email);

    List<Programmer> findTop20ByOrderBySolutionPointsDesc();

    List<Programmer> findTop20ByOrderByTaskPointsDesc();

}
