package com.books.api.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Roles {

    USER(1, "user"),
    ADMIN(2, "admin");

    private int id;
    private String name;
}
