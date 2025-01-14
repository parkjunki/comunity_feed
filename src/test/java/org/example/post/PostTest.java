package org.example.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.post.domain.Post;
import org.example.post.domain.content.PostContent;
import org.example.post.domain.content.PostPublicationState;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class PostTest {

    private final UserInfo userInfo = new UserInfo("name" ,"");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);

    private final Post post = new Post(1L, user , new PostContent("content"));

    @Test
    void givenPostCreated_whenLike_thenLikeCountShouldBe1() {
        //when
        post.like(otherUser);

        //then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeBySelf_thenThrowError() {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreatedAndLike_whenUnlike_thenLikeCountShouldBe0() {
        //given
        post.like(otherUser);

        //when
        post.unlike();

        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUnlike_thenLikeCountShouldBe0() {
        //when
        post.unlike();

        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        //given
        String text = "this is a text";

        //when
        post.updatePost(user, text, PostPublicationState.PUBLIC);

        //then
        assertEquals(text, post.getContent());
    }

    @Test
    void givenPostCreated_whenUpdateOtherUserUpdate_thenThrowError() {
        //given
        String text = "update text";

        //when, then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, text, PostPublicationState.PUBLIC));
    }
}
