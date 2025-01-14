package org.example.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommentContentTest {
    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContent() {
        //given
        String text = "this is a test";

        //when
        CommentContent commentContent = new CommentContent(text);

        //then
        assertEquals(text, commentContent.getContentText());
    }


}
