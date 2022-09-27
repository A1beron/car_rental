package com.books.service.menu;

import com.books.api.menu.MenuOption;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.books.AppContext.getActiveUser;

public class MenuService implements MenuOptionsProvider {

    public List<MenuOption> getOptionsAvailableForTheUser() {
        return Arrays.stream(MenuOption.values())
                .filter(menuOption ->
                        getActiveUser().getRoles().contains(menuOption.getNecessaryRole()))
                .distinct()
                .collect(Collectors.toList());
    }
}
