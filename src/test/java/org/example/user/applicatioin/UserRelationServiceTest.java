package org.example.user.applicatioin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.user.application.UserRelationService;
import org.example.user.application.UserService;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.application.dto.FollowUserRequestDto;
import org.example.user.application.interfaces.UserRelationRepository;
import org.example.user.application.interfaces.UserRepository;
import org.example.user.domain.User;
import org.example.user.repository.FakeUserRelationRepository;
import org.example.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRelationServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;
    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        user1 = userService.createUser(dto);
        user2 = userService.createUser(dto);

        requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        //when
        userRelationService.follow(requestDto);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenThrowError() {
        //given
        userRelationService.follow(requestDto);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenThrowError() {
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(),
                user1.getId());

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

    @Test
    void givenCreateTwoUser_whenUnFollow_thenUserUnFollowSaved() {
        //given
        userRelationService.follow(requestDto);

        //when
        userRelationService.unfollow(requestDto);

        //then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenUnFollow_thenThrowError() {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenUnFollowSelf_thenThrowError() {
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(),
                user1.getId());

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));
    }
}
