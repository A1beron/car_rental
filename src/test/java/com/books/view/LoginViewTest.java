package com.books.view;

import com.books.api.user.UserLoginData;
import com.books.controller.user.UserLoginControllerImpl;
import com.books.model.Role;
import com.books.model.User;
import com.books.service.user.UserService;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class LoginViewTest {

    private final UserLoginData testUserLoginData = new UserLoginData(
            "testUser",
            "testPassword"
    );

    @Test
    void shouldProvideUserLoginDataBasedOnScannerInput() throws FileNotFoundException {
        LoginView loginView = new LoginView(
                new File("src/test/resources/view/login/loginInput.txt"),
                Optional.empty(),
                null //irrelevant to test case
        );
        assertThat(loginView.getData()).isEqualTo(testUserLoginData);
    }
}