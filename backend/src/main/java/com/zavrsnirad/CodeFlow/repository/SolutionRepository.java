package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

    @Query("SELECT s FROM Solution s WHERE s.task.taskId = :taskId")
    List<Solution> findByTask(@Param("taskId") Long taskId);

}
