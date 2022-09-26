package com.books.view;

import com.books.api.address.City;
import com.books.api.user.UserData;
import com.books.controller.user.UserController;
import com.books.controller.user.UserControllerImpl;
import com.books.service.city.CityByPostalCodeProvider;
import com.books.service.city.CityService;
import com.books.util.DisplayUtil;
import com.books.view.validators.UserDataValidator;
import com.books.view.validators.UserDataValidatorImpl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreateUserViewImpl implements View {

    private Scanner scanner;
    private UserController userController;
    private UserDataValidator userDataValidator;
    private CityByPostalCodeProvider cityProvider;

    public CreateUserViewImpl() {
        scanner = new Scanner(System.in);
        userController = new UserControllerImpl();
        userDataValidator = new UserDataValidatorImpl(scanner);
        cityProvider = new CityService();
    }

    public CreateUserViewImpl(
            InputStream in,
            UserController userController,
            UserDataValidator userDataValidator,
            CityByPostalCodeProvider cityProvider
    ) {
        scanner = new Scanner(in);
        this.userController = userController;
        this.userDataValidator = userDataValidator;
        this.cityProvider = cityProvider;
    }

    @Override
    public void display() {
        userController.createUser(createNewUser()).display();
    }

    private UserData createNewUser() {
        UserData userData = new UserData();
        getAndVerifyLogin(userData);
        getAndVerifyEmail(userData);
        System.out.println("Podaj hasło");
        userData.setPassword(scanner.nextLine());
        System.out.println("Podaj imię");
        userData.setFirstName(scanner.nextLine());
        System.out.println("Podaj nazwisko");
        userData.setLastName(scanner.nextLine());
        getAndVerifyPostalCode(userData);
        System.out.println("Podaj ulicę");
        userData.setStreet(scanner.nextLine());
        System.out.println("Podaj numer domu");
        userData.setStreet(scanner.nextLine());
        System.out.println("Podaj numer mieszkania");
        userData.setStreet(scanner.nextLine());
        return userData;
    }

    public void getAndVerifyLogin(UserData userData) {
        System.out.println("Podaj login nowego użytkownika");
        String login = scanner.nextLine();
        userData.setLogin(login);
        if (userDataValidator.isLoginValid(login)) {
            System.out.println("Login użytkownika już istnieje");
            getAndVerifyLogin(userData);
        }
    }

    public void getAndVerifyEmail(UserData userData) {
        System.out.println("Podaj email");
        String email = scanner.nextLine();
        userData.setEmail(email);
        if (userDataValidator.isEmailValid(email)) {
            System.out.println("Niepoprawny format email");
            getAndVerifyEmail(userData);
        }
    }

    public void getAndVerifyPostalCode(UserData userData) {
        System.out.println("Podaj kod pocztowy");
        String postalCode = scanner.nextLine();
        userData.setPostalCode(postalCode);
        if (userDataValidator.isPostalCodeValid(postalCode)) {
            System.out.println("Niepoprawny format kodu pocztowego");
            getAndVerifyCity(userData);
        }
    }

    private void getAndVerifyCity(UserData userData) {
        Set<City> cities = cityProvider.getCityNamesFromPostalCode(userData.getPostalCode());
        if (cities.isEmpty()) {
            System.out.println("Nie znaleziono miasta dla podanego kodu pcoztowego, podaj miasto:");
            userData.setCity(scanner.nextLine());
        }
        if (cities.size() > 1) {
            userData.setCity(selectCity(cities));
        } else {
            userData.setCity(cities.stream().findFirst().get().getName());
        }
    }

    private String selectCity(Set<City> cities) {
        DisplayUtil<City> displayUtil = new DisplayUtil<>();
        System.out.println("Dla danego kodu pocztowego znaleziono wiele miast, wybierz swoje miasto:");
        Map<String, City> mappedCities = displayUtil.mapForDisplay(cities);
        mappedCities.forEach((integer, city) -> System.out.println(integer + ". "+city));
        String selection = scanner.nextLine();
        if(!mappedCities.containsKey(selection)) {
            System.out.println("Wybrano nieprawidłową wartości");
            selectCity(cities);
        }
        return mappedCities.get(selection).getName();
    }
}
