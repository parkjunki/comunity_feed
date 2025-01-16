package org.example.user.applicatioin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.fake.FakeObjectFactory;
import org.example.user.application.UserService;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.application.interfaces.UserRepository;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.example.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

class UserServiceTest {
    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        //when
        User savedUser = userService.createUser(dto);

        //then
        User findUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = findUser.getInfo();
        assertEquals(findUser.getId(), savedUser.getId());
        assertEquals("test", userInfo.getName());
    }
}
