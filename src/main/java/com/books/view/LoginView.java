package com.books.view;

import com.books.api.user.UserLoginData;
import com.books.controller.user.UserLoginController;
import com.books.controller.user.UserLoginControllerImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

public class LoginView implements View<UserLoginData> {

    private Scanner scanner;
    private final Optional<String> message;
    private final UserLoginController userLoginController;

    public LoginView(Optional<String> message) {
        scanner = new Scanner(System.in);
        this.message = message;
        this.userLoginController = new UserLoginControllerImpl();
    }

    public LoginView(
            File inputFile,
            Optional<String> message,
            UserLoginController userLoginController
    ) throws FileNotFoundException {
        scanner = new Scanner(inputFile);
        this.message = message;
        this.userLoginController = userLoginController;
    }

    @Override
    public void display() {
        userLoginController.login(getData()).display();
    }

    @Override
    public UserLoginData getData() {
        message.ifPresent(System.out::println);
        UserLoginData userLoginData = new UserLoginData();
        System.out.println("Podaj login");
        userLoginData.setLogin(scanner.nextLine());
        System.out.println("Podaj hasło");
        userLoginData.setPassword(scanner.nextLine());

        return userLoginData;
    }

}
