package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t " +
            "FROM Task t " +
            "WHERE t.owner.username = :username " +
            "ORDER BY t.modified DESC")
    List<Task> findAllByUsername(@Param("username") String username);

    @Query("SELECT t " +
            "FROM Task t " +
            "INNER JOIN t.solutions as solutions " +
            "INNER JOIN solutions.author as author " +
            "WHERE author.username = :username " +
            "ORDER BY t.modified DESC" )
    List<Task> findSolvedByUsername(@Param("username") String username);

    List<Task> findAllByOrderByModifiedDesc();

    @Query("SELECT distinct t " +
            "FROM Task t " +
            "INNER JOIN t.owner as owner " +
            "LEFT OUTER JOIN owner.followers as followers " +
            "WHERE ((followers.follower.programmerId = :programmerId AND followers.pending = false) OR t.averageGrade >= 3) " +
            "AND t.owner.programmerId != :programmerId " +
            "ORDER BY t.modified DESC")
    List<Task> recommendedTasks(@Param("programmerId") Long programmerId);

    @Query("SELECT t " +
            "FROM Task t " +
            "INNER JOIN t.owner as owner " +
            "INNER JOIN owner.followers as followers " +
            "WHERE followers.follower.programmerId = :programmerId AND followers.pending = false " +
            "ORDER BY t.modified DESC")
    List<Task> tasksFromFollowed(@Param("programmerId") Long programmerId);
}
