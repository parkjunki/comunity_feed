package org.example.post.repository.post_queue;

import java.util.List;
import org.example.post.ui.dto.GetPostContentResponseDto;

public interface UserPostQueueQueryRepository {

    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);
}
