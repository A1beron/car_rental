package com.books.api.user;

import com.books.api.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.util.Set;

@Getter
@Immutable
@AllArgsConstructor
public class ActiveUser {

    private final String login;
    private final String firstName;
    private final String lastName;
    private final Set<UserRole> roles;

}
