package org.example.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.common.domain.PositiveIntegerCount;
import org.example.common.repository.entity.TimeBaseEntity;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class UserEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImageUrl;
    private Integer followerCount;
    private Integer followingCount;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImageUrl = user.getProfileImageUrl();
        this.followerCount = user.followerCount();
        this.followingCount = user.followingCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .info(new UserInfo(name, profileImageUrl))
                .followerCounter(new PositiveIntegerCount(followerCount))
                .followingCounter(new PositiveIntegerCount(followingCount))
                .build();
    }
}
