package com.books.view;

import com.books.api.user.UserData;
import com.books.controller.user.UserController;
import com.books.controller.user.UserControllerImpl;
import com.books.view.validators.UserDataValidator;
import com.books.view.validators.UserDataValidatorImpl;

import java.io.InputStream;
import java.util.Scanner;

public class CreateUserViewImpl implements View {

    private Scanner scanner;
    private UserController userController;
    private UserDataValidator userDataValidator;

    public CreateUserViewImpl() {
        scanner = new Scanner(System.in);
        userController = new UserControllerImpl();
        userDataValidator = new UserDataValidatorImpl(scanner);
    }

    public CreateUserViewImpl(
            InputStream in,
            UserController userController,
            UserDataValidator userDataValidator
    ) {
        scanner = new Scanner(in);
        this.userController = userController;
        this.userDataValidator = userDataValidator;
    }

    @Override
    public void display() {
        userController.createUser(createNewUser()).display();
    }

    private UserData createNewUser() {
        UserData userData = new UserData();
        userDataValidator.getAndVerifyLogin(userData);
        userDataValidator.getAndVerifyEmail(userData);
        System.out.println("Podaj hasło");
        userData.setPassword(scanner.nextLine());
        System.out.println("Podaj imię");
        userData.setFirstName(scanner.nextLine());
        System.out.println("Podaj nazwisko");
        userData.setLastName(scanner.nextLine());
        userDataValidator.getAndVerifyPostalCode(userData);
        System.out.println("Podaj ulicę");
        userData.setStreet(scanner.nextLine());
        System.out.println("Podaj numer domu");
        userData.setStreet(scanner.nextLine());
        System.out.println("Podaj numer mieszkania");
        userData.setStreet(scanner.nextLine());
        return userData;
    }


}
