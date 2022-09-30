package com.books.service.user;

import com.books.api.user.UserLoginData;
import com.books.model.User;

import java.util.Optional;

public interface UserLoginChecker {

    Optional<User> loginAndGet(UserLoginData userLoginData);

}
