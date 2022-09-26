package com.books.view;

import com.books.api.address.City;
import com.books.api.user.UserData;
import com.books.controller.user.UserController;
import com.books.controller.user.UserControllerImpl;
import com.books.service.city.CityByPostalCodeProvider;
import com.books.service.city.CityService;
import com.books.view.validators.UserDataValidator;
import com.books.view.validators.UserDataValidatorImpl;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static com.books.util.DisplayUtil.canBeSelected;
import static com.books.util.DisplayUtil.mapAndDisplay;
import static java.util.stream.Collectors.toSet;

public class CreateUserViewImpl implements View {

    private Scanner scanner;
    private UserController userController;
    private UserDataValidator userDataValidator;
    private CityByPostalCodeProvider cityProvider;

    public CreateUserViewImpl() {
        scanner = new Scanner(System.in);
        userController = new UserControllerImpl();
        userDataValidator = new UserDataValidatorImpl();
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
        userData.setBuildingNo(scanner.nextLine());
        System.out.println("Podaj numer mieszkania");
        userData.setApartmentNo(scanner.nextLine());
        return userData;
    }

    public void getAndVerifyLogin(UserData userData) {
        System.out.println("Podaj login nowego użytkownika");
        String login = scanner.nextLine();
        userData.setLogin(login);
        if (!userDataValidator.isLoginValid(login)) {
            System.out.println("Login użytkownika już istnieje");
            getAndVerifyLogin(userData);
        }
    }

    public void getAndVerifyEmail(UserData userData) {
        System.out.println("Podaj email");
        String email = scanner.nextLine();
        userData.setEmail(email);
        if (!userDataValidator.isEmailValid(email)) {
            System.out.println("Niepoprawny format email");
            getAndVerifyEmail(userData);
        }
    }

    public void getAndVerifyPostalCode(UserData userData) {
        System.out.println("Podaj kod pocztowy");
        String postalCode = scanner.nextLine();
        userData.setPostalCode(postalCode);
        if (!userDataValidator.isPostalCodeValid(postalCode)) {
            System.out.println("Podano kod pocztowy o nieprawidłowym formacie");
            getAndVerifyPostalCode(userData);
        }
        getAndVerifyCity(userData);
    }

    private void getAndVerifyCity(UserData userData) {
        Set<City> cities = cityProvider.getCityNamesFromPostalCode(userData.getPostalCode());

        if (cities.isEmpty()) {
            System.out.println("Podano nieistniejący kod pocztowy");
            getAndVerifyPostalCode(userData);
            return;
        }
        if (userDataValidator.hasMultipleCities(cities)) {
            userData.setCity(selectCity(cities));
        } else {
            userData.setCity(cities.stream().findFirst().get().getName());
        }
        System.out.println("Wybrano miasto: " + userData.getCity());
    }

    private String selectCity(Set<City> cities) {
        System.out.println("Dla danego kodu pocztowego znaleziono wiele miast, wybierz swoje miasto:");
        Set<String> cityNames = cities.stream()
                .map(City::getName)
                .collect(toSet());
        Map<Integer, String> mappedCities = mapAndDisplay(cityNames);
        String selection = scanner.nextLine();
        if (canBeSelected(mappedCities, selection)) {
            return mappedCities.get(Integer.parseInt(selection));
        }
        System.out.println("Wybrano nieprawidłową wartości");
        return selectCity(cities);
    }

}
