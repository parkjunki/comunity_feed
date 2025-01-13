package org.example.user.domain;

import java.util.Objects;
import org.example.common.domain.PositiveIntegerCount;

public class User {
    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCount followingCounter;
    private final PositiveIntegerCount followerCounter;


    public User(Long id, UserInfo userInfo) {
        if(userInfo == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.info = userInfo;
        this.followingCounter = new PositiveIntegerCount();
        this.followerCounter = new PositiveIntegerCount ();
    }

    public void follow(User targetUser)  {
        if(targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followingCounter.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targerUser) {
        if(this.equals(targerUser)) {
            throw new IllegalArgumentException();
        }

        followingCounter.decrease();
        targerUser.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followerCounter.increase();
    }

    private void decreaseFollowerCount() {
        followerCounter.decrease();
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int followerCount() {
        return followerCounter.getCount();
    }

    public int followingCount() {
        return followingCounter.getCount();
    }

    public UserInfo getInfo() {
        return info;
    }
}
