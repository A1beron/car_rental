package com.books.dao.user;

import com.books.model.User;

import java.util.Optional;

public interface UserProvider {

    Optional<User> findUserByLogin(String login);

}
