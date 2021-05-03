package com.zavrsnirad.CodeFlow.repository;

import com.zavrsnirad.CodeFlow.domain.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    @Query("SELECT f FROM FOLLOWERSHIP f WHERE f.programmer.programmerId = :followedId AND f.follower.programmerId = :followerId")
    Follower findByFollowedAndFollowerId(@Param("followedId") Long followedId, @Param("followerId") Long followerId);

}
