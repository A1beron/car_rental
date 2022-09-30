package com.books;

import com.books.api.role.UserRole;
import com.books.api.user.ActiveUser;
import com.books.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class AppContext {

    private static ActiveUser activeUser;

    public static void setActiveUser(User user) {
        activeUser = new ActiveUser(
                user.getLogin(),
                user.getFirstName(),
                user.getLastName(),
                getEnumRoles(user)
        );
    }

    public static ActiveUser getActiveUser() {
        return activeUser;
    }

    private static Set<UserRole> getEnumRoles(User user) {
        return user.getRoles()
                .stream()
                .flatMap(role -> UserRole.find(role.getName()).stream())
                .collect(Collectors.toSet());
    }

}
