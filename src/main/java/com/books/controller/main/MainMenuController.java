package com.books.controller.main;

import com.books.api.menu.MenuOption;
import com.books.view.View;

import java.util.Map;
import java.util.Optional;

public interface MainMenuController {

    Map<Integer, MenuOption> getAvailableOptions();

    View selectOption(Optional<MenuOption> menuOption);

}
