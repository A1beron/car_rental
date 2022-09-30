package com.books.controller.main;

import com.books.api.menu.MenuOption;
import com.books.service.menu.MenuOptionsProvider;
import com.books.service.menu.MenuService;
import com.books.util.DisplayUtil;
import com.books.view.BooksView;
import com.books.view.CreateUserView;
import com.books.view.MainMenuView;
import com.books.view.View;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class MainMenuControllerImpl implements MainMenuController {

    private final MenuOptionsProvider menuOptionsProvider;

    public MainMenuControllerImpl() {
        menuOptionsProvider = new MenuService();
    }

    public Map<Integer, MenuOption> getAvailableOptions() {
        List<MenuOption> menuOptions = menuOptionsProvider.getOptionsAvailableForTheUser();
        DisplayUtil<MenuOption> displayUtil = new DisplayUtil<>();
        return displayUtil.convertToMap(menuOptions);
    }

    @Override
    public View<?> selectOption(Optional<MenuOption> menuOption) {
        if (menuOption.isPresent()) {
            switch (menuOption.get()) {
                case LIST_BOOKS: return new BooksView();
                case CREATE_USER: return new CreateUserView();
                default: return new MainMenuView();
            }
        }
        return new MainMenuView();
    }

}
