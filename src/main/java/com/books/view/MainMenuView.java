package com.books.view;

import com.books.api.menu.MenuOption;
import com.books.controller.main.MainMenuController;
import com.books.controller.main.MainMenuControllerImpl;
import com.books.util.DisplayUtil;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;


public class MainMenuView implements View<Optional<MenuOption>> {

    private final Scanner scanner;
    private final MainMenuController mainMenuController;

    public MainMenuView(InputStream in, MainMenuController mainMenuController) {
        this.scanner = new Scanner(in);
        this.mainMenuController = mainMenuController;
    }

    public MainMenuView() {
        this.scanner = new Scanner(System.in);
        this.mainMenuController = new MainMenuControllerImpl();
    }

    @Override
    public void display() {
        mainMenuController.selectOption(getData());
    }

    @Override
    public Optional<MenuOption> getData() {
        System.out.println("Wybierz co chcesz zrobić: ");
        Map<Integer, MenuOption> availableOptions = mainMenuController.getAvailableOptions();
        availableOptions.forEach((integer, menuOption) -> System.out.println(integer + ". " + menuOption.getText()));
        DisplayUtil<MenuOption> displayUtil = new DisplayUtil<>();
        String selectedValue = scanner.nextLine();
        if (displayUtil.canBeSelected(availableOptions, selectedValue)) {
            return Optional.of(
                    availableOptions.get(
                            Integer.parseInt(selectedValue)
                    )
            );
        } else {
            return Optional.empty();
        }
    }

}
