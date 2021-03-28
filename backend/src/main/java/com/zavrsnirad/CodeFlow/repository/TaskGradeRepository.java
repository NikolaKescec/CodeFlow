package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.TaskGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskGradeRepository extends JpaRepository<TaskGrade, Long> {
}
