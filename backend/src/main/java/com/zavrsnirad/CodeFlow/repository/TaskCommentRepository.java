package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {

    @Query("SELECT c FROM TASK_COMMENT c WHERE c.task.taskId = :taskId")
    List<TaskComment> findCommentByTaskId(@Param("taskId") Long taskId);

}
