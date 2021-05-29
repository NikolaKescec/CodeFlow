package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.TaskGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskGradeRepository extends JpaRepository<TaskGrade, Long> {

    @Query("SELECT tg FROM TASK_GRADE tg WHERE tg.grader.programmerId = :graderId AND tg.task.taskId = :taskId")
    TaskGrade findByTaskIdAndGraderId(@Param("taskId") Long taskId, @Param("graderId") Long graderId);

}
