package com.books.controller.user;

import com.books.api.user.UserLoginData;
import com.books.model.Role;
import com.books.model.User;
import com.books.service.user.UserService;
import com.books.view.LoginView;
import com.books.view.MainMenuView;
import com.books.view.View;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.books.AppContext.getActiveUser;
import static com.books.api.role.UserRole.USER;
import static org.assertj.core.api.Assertions.assertThat;

class UserLoginControllerImplTest {

    private final static String EXISTING_USER_LOGIN = "login";
    private final static String NOT_EXISTING_USER_LOGIN = "incorrectLogin";
    private final static String CORRECT_PASSWORD = "password";
    private final static String INCORRECT_PASSWORD = "incorrectPassword";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";

    private final User testUser = new User(
            1,
            EXISTING_USER_LOGIN,
            "email",
            CORRECT_PASSWORD,
            FIRST_NAME,
            LAST_NAME,
            List.of(new Role(1, "user")),
            null,
            List.of()
    );

    private final UserLoginController userLoginController = new UserLoginControllerImpl(
            new UserService(
                    login -> {
                        if (login.equals(EXISTING_USER_LOGIN)) {
                            return Optional.of(testUser);
                        } else {
                            return Optional.empty();
                        }
                    }
            )
    );

    @Test
    void forExistingUserAndCorrectPasswordShouldReturnMainMenuViewAndSetUpContextUser() {
        View<?> result = userLoginController.login(new UserLoginData(EXISTING_USER_LOGIN, CORRECT_PASSWORD));
        assertThat(result).isInstanceOf(MainMenuView.class);
        assertThat(getActiveUser().getLogin()).isEqualTo(EXISTING_USER_LOGIN);
        assertThat(getActiveUser().getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(getActiveUser().getLastName()).isEqualTo(LAST_NAME);
        assertThat(getActiveUser().getRoles()).isEqualTo(Set.of(USER));
    }

    @Test
    void forExistingUserAndIncorrectPasswordShouldReturnLoginView() {
        View<?> result = userLoginController.login(new UserLoginData(EXISTING_USER_LOGIN, INCORRECT_PASSWORD));
        assertThat(result).isInstanceOf(LoginView.class);
    }

    @Test
    void forNotExistingUserShouldReturnLoginView() {
        View<?> result = userLoginController.login(new UserLoginData(NOT_EXISTING_USER_LOGIN, INCORRECT_PASSWORD));
        assertThat(result).isInstanceOf(LoginView.class);
    }

}