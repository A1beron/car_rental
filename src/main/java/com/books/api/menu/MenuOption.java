package com.books.api.menu;

import com.books.api.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.books.api.role.UserRole.ADMIN;
import static com.books.api.role.UserRole.USER;

@AllArgsConstructor
@Getter
public enum MenuOption {

    CREATE_USER("Dodaj nowego użytkownika", ADMIN),
    LIST_BOOKS("Wyświetl dostępne książki", USER);

    private final String text;
    private final UserRole necessaryRole;

}
