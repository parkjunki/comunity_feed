package org.example.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.post.application.dto.LikeRequestDto;
import org.example.post.application.dto.UpdateCommentRequestDto;
import org.example.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenCommentRequestDto_whenCreateComment_thenReturnComment() {
        //when
        Comment comment = commentService.createComment(commentRequestDto);

        //then
        String content = comment.getContent();
        assertEquals(commentContentText, content);
    }

    @Test
    void givenCommentRequestDto_whenUpdateComment_thenReturnPost() {
        //given
        Comment savedComment = commentService.createComment(commentRequestDto);
        //when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(savedComment.getId(), user.getId(), "update comment");
        Comment updatedComment = commentService.updateComment(updateCommentRequestDto);
        //then
        assertEquals(savedComment.getId(), updatedComment.getId());
        assertEquals(savedComment.getAuthor(), updatedComment.getAuthor());
        assertEquals(savedComment.getContent(), updatedComment.getContent());
    }

    @Test
    void givenCreateComment_whenLikeComment_thenReturnCommentWithLike() {
        //given
        Comment savedComment = commentService.createComment(commentRequestDto);
        //when
        LikeRequestDto dto = new LikeRequestDto(savedComment.getId(), otherUser.getId());
        commentService.likeComment(dto);
        //then
        assertEquals(1, savedComment.getLikeCount());
    }

    @Test
    void givenCreateComment_whenUnlikeComment_thenReturnCommentWithLike() {
        //given
        Comment savedComment = commentService.createComment(commentRequestDto);
        //when
        LikeRequestDto dto = new LikeRequestDto(savedComment.getId(), otherUser.getId());
        commentService.likeComment(dto);
        commentService.unlikeComment(dto);
        //then
        assertEquals(0, savedComment.getLikeCount());
    }
}
