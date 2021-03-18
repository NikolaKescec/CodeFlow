package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findTop20ByOrderBySolutionPointsDesc();

    List<User> findTop20ByOrderByTaskPointsDesc();

}
