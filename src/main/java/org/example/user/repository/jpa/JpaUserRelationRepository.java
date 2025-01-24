package org.example.user.repository.jpa;

import java.util.List;
import org.example.user.repository.entity.UserRelationEntity;
import org.example.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
    @Query("SELECT ur.followingUserId FROM UserRelationEntity ur WHERE ur.followerUserId = :userId")
    List<Long> findFollowers(Long userId);
}
