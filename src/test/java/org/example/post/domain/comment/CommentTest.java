package org.example.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.post.domain.Post;
import org.example.post.domain.content.CommentContent;
import org.example.post.domain.content.PostContent;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class CommentTest {
    private final UserInfo userInfo = new UserInfo("name" ,"");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);

    private final Post post = new Post(1L, user , new PostContent("content"));
    private final Comment comment = new Comment(1L,post, user, new CommentContent("comment_content"));

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1() {
        //when
        comment.like(otherUser);

        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenUnlike_thenLikeCountShouldBe0() {
        //when
        comment.unlike();

        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        //given
        String text = "this is a text";

        //when
        comment.updateComment(user, text);

        //then
        assertEquals(text, comment.getContent());
    }

    @Test
    void givenCommentCreated_whenUpdateOtherUserUpdate_thenThrowError() {
        //given
        String text = "update text";

        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(otherUser, text));
    }
}
