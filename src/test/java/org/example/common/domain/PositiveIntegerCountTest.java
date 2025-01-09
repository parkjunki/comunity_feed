package org.example.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PositiveIntegerCountTest {
    @Test
    void givenCreated_whenIncrease_thenCountIsOne() {
        //given
        PositiveIntegerCount count = new PositiveIntegerCount();

        //when
        count.increase();

        //then
        assertEquals(1, count.getCount());
    }

    @Test
    void givenCreatedAndIncreased_whenDecrease_thenCountIsZero() {
        //given
        PositiveIntegerCount count = new PositiveIntegerCount();
        count.increase();

        //when
        count.decrease();

        //then
        assertEquals(0, count.getCount());
    }

    @Test
    void givenCreated_whenDecrease_thenCountIsZero() {
        //given
        PositiveIntegerCount count = new PositiveIntegerCount();

        //when
        count.decrease();

        //then
        assertEquals(0, count.getCount());
    }
}
