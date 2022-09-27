package com.books.api.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum UserRole {

    USER(1, "user"),
    ADMIN(2, "admin");

    private int id;
    private String name;

    public static Optional<UserRole> find(String name) {
        return Arrays.stream(values())
                .filter(userRole -> userRole.getName().equals(name))
                .findFirst();
    }

}
