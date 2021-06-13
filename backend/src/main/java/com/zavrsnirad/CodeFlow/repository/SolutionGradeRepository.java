package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.SolutionGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SolutionGradeRepository extends JpaRepository<SolutionGrade, Long> {

    @Query("SELECT sg FROM SOLUTION_GRADE sg WHERE sg.grader.programmerId = :graderId AND sg.solution.solutionId = :solutionId")
    SolutionGrade findBySolutionIdAndGraderId(@Param("solutionId") Long solutionId, @Param("graderId") Long graderId);

}
