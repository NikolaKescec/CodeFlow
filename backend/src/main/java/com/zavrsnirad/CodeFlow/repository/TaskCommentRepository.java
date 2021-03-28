package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {
}
