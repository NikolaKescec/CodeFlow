package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.RefreshToken;
import com.zavrsnirad.CodeFlow.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, User> {
    RefreshToken findByRefreshToken(UUID refreshToken);

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.user.id = :uid")
    RefreshToken findByUserId(@Param("uid") UUID userId);

}
