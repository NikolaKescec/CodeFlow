package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.owner.username = :username ORDER BY t.modified DESC")
    List<Task> findAllByUsername(@Param("username") String username);

    @Query("SELECT t FROM Task t INNER JOIN t.solutions as solutions INNER JOIN solutions.author as author WHERE author.username = :username ORDER BY t.modified DESC" )
    List<Task> findSolvedByUsername(@Param("username") String username);

    List<Task> findAllByOrderByModifiedDesc();
}
