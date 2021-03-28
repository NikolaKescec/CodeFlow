package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.SolutionComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionCommentRepository extends JpaRepository<SolutionComment, Long> {
}
