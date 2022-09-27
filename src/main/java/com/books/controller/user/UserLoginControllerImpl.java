package com.books.controller.user;

import com.books.api.user.UserLoginData;
import com.books.model.User;
import com.books.service.user.UserLoginChecker;
import com.books.service.user.UserService;
import com.books.view.LoginView;
import com.books.view.MainMenuView;
import com.books.view.View;
import lombok.AllArgsConstructor;

import java.util.Optional;

import static com.books.AppContext.setActiveUser;

@AllArgsConstructor
public class UserLoginControllerImpl implements UserLoginController {

    private final UserLoginChecker userLoginChecker;

    public UserLoginControllerImpl() {
        userLoginChecker = new UserService();
    }

    @Override
    public View login(UserLoginData userLoginData) {
        Optional<User> user = userLoginChecker.loginAndGet(userLoginData);
        if (user.isPresent()) {
            setActiveUser(user.get());
            return new MainMenuView();
        }
        return new LoginView(Optional.of("Niepoprawne hasło lub login"));
    }

}

