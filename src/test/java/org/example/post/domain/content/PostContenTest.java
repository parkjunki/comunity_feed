package org.example.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContenTest {
    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        //given
        String text = "this is a test";

        //when
        PostContent postContent = new PostContent(text);

        //then
        assertEquals(text, postContent.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError() {
        //given
        String text = "a".repeat(501);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁","슭","곪","콮","샾"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreanWord) {
        //given
        String text = koreanWord.repeat(501);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenThrowError() {
        //given
        String text = "a".repeat(4);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmpty_whenCreated_thenThrowError(String value) {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }
}
