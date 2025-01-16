package org.example.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.post.application.dto.LikeRequestDto;
import org.example.post.application.dto.UpdatePostRequestDto;
import org.example.post.domain.Post;
import org.example.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.Test;

class PostServiceTest extends PostApplicationTestTemplate{

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        //when
        Post savedPost = postService.createPost(postRequestDto);

        //then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnPost() {
        //given
        Post savedPost = postService.createPost(postRequestDto);

        //when
        Post updatedPost = postService.updatePost(new UpdatePostRequestDto(savedPost.getId(), user.getId(), "update content", PostPublicationState.PUBLIC));

        //then
        assertEquals(savedPost.getId(), updatedPost.getId());
        assertEquals(savedPost.getAuthor(), updatedPost.getAuthor());
        assertEquals(savedPost.getContent(), updatedPost.getContent());
    }

    @Test
    void givenCreatePost_whenLiked_thenReturnPostWithLike() {
        //given
        Post savedPost = postService.createPost(postRequestDto);
        //when
        LikeRequestDto dto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(dto);
        //then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePostLiked_whenUnliked_thenReturnPostWithLike() {
        //given
        Post savedPost = postService.createPost(postRequestDto);
        //when
        LikeRequestDto dto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(dto);
        postService.unlikePost(dto);
        //then
        assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePost_whenUnliked_thenReturnPostWithLike() {
        //given
        Post savedPost = postService.createPost(postRequestDto);
        //when
        LikeRequestDto dto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.unlikePost(dto);
        //then
        assertEquals(0, savedPost.getLikeCount());
    }
}
