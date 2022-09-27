package com.books.service.user;

import com.books.api.user.UserLoginData;
import com.books.dao.user.UserDao;
import com.books.dao.user.UserProvider;
import com.books.model.User;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UserService implements UserLoginExistChecker, UserLoginChecker {

    private final UserProvider userProvider;

    public UserService() {
        userProvider = new UserDao();
    }

    @Override
    public boolean userLoginExist(String login) {
        return userProvider.findUserByLogin(login).isPresent();
    }

    @Override
    public Optional<User> loginAndGet(UserLoginData userLoginData) {
        Optional<User> providedUser = userProvider.findUserByLogin(userLoginData.getLogin());
        return providedUser
                .filter(user -> checkPassword(userLoginData, user));
    }

    private boolean checkPassword(UserLoginData userLoginData, User user) {
        return user.getPassword().equals(userLoginData.getPassword());
    }
}
