package org.example.post.repository.post_queue;

import java.util.List;
import org.example.post.repository.entity.post.PostEntity;

public interface UserQueueRedisRepository {

    void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList);
    void publishPostListToFollowerUserList(List<PostEntity> postEntityList, Long userId);
    void deleteDeleteFeed(Long userId, Long authorId);


}
