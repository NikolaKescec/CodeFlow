package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.SolutionComment;
import com.zavrsnirad.CodeFlow.domain.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolutionCommentRepository extends JpaRepository<SolutionComment, Long> {

    @Query("SELECT s FROM SOLUTION_COMMENT s WHERE s.solution.solutionId = :solutionId")
    List<SolutionComment> findCommentBySolutionId(@Param("solutionId") Long solutionId);

}
