package com.books.service.menu;

import com.books.api.menu.MenuOption;

import java.util.List;

public interface MenuOptionsProvider {

    List<MenuOption> getOptionsAvailableForTheUser();

}
