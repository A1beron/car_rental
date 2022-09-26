package com.books.service.user;

import com.books.dao.user.UserDao;
import com.books.dao.user.UserProvider;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService implements UserLoginExistChecker {

    private final UserProvider userProvider;

    public UserService() {
        userProvider = new UserDao();
    }

    @Override
    public boolean userLoginExist(String login) {
        return userProvider.findUserByLogin(login).isPresent();
    }

}
