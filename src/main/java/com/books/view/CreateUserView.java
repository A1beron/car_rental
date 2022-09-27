package com.books.view;

import com.books.api.address.City;
import com.books.api.user.NewUserData;
import com.books.controller.user.CreateUserController;
import com.books.controller.user.CreateUserControllerImpl;
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

import static java.util.stream.Collectors.toList;

public class CreateUserView implements View<NewUserData> {

    private Scanner scanner;
    private CreateUserController createUserController;
    private UserDataValidator userDataValidator;
    private CityByPostalCodeProvider cityProvider;

    public CreateUserView() {
        scanner = new Scanner(System.in);
        createUserController = new CreateUserControllerImpl();
        userDataValidator = new UserDataValidatorImpl();
        cityProvider = new CityService();
    }

    public CreateUserView(
            InputStream in,
            CreateUserController createUserController,
            UserDataValidator userDataValidator,
            CityByPostalCodeProvider cityProvider
    ) {
        scanner = new Scanner(in);
        this.createUserController = createUserController;
        this.userDataValidator = userDataValidator;
        this.cityProvider = cityProvider;
    }

    @Override
    public void display() {
        createUserController.createUser(getData()).display();
    }

    @Override
    public NewUserData getData() {
        return createNewUser();
    }

    private NewUserData createNewUser() {
        NewUserData newUserData = new NewUserData();
        getAndVerifyLogin(newUserData);
        getAndVerifyEmail(newUserData);
        System.out.println("Podaj hasło");
        newUserData.setPassword(scanner.nextLine());
        System.out.println("Podaj imię");
        newUserData.setFirstName(scanner.nextLine());
        System.out.println("Podaj nazwisko");
        newUserData.setLastName(scanner.nextLine());
        getAndVerifyPostalCode(newUserData);
        System.out.println("Podaj ulicę");
        newUserData.setStreet(scanner.nextLine());
        System.out.println("Podaj numer domu");
        newUserData.setBuildingNo(scanner.nextLine());
        System.out.println("Podaj numer mieszkania");
        newUserData.setApartmentNo(scanner.nextLine());
        return newUserData;
    }

    public void getAndVerifyLogin(NewUserData newUserData) {
        System.out.println("Podaj login nowego użytkownika");
        String login = scanner.nextLine();
        newUserData.setLogin(login);
        if (!userDataValidator.isLoginValid(login)) {
            System.out.println("Login użytkownika już istnieje");
            getAndVerifyLogin(newUserData);
        }
    }

    public void getAndVerifyEmail(NewUserData newUserData) {
        System.out.println("Podaj email");
        String email = scanner.nextLine();
        newUserData.setEmail(email);
        if (!userDataValidator.isEmailValid(email)) {
            System.out.println("Niepoprawny format email");
            getAndVerifyEmail(newUserData);
        }
    }

    public void getAndVerifyPostalCode(NewUserData newUserData) {
        System.out.println("Podaj kod pocztowy");
        String postalCode = scanner.nextLine();
        newUserData.setPostalCode(postalCode);
        if (!userDataValidator.isPostalCodeValid(postalCode)) {
            System.out.println("Podano kod pocztowy o nieprawidłowym formacie");
            getAndVerifyPostalCode(newUserData);
        }
        getAndVerifyCity(newUserData);
    }

    private void getAndVerifyCity(NewUserData newUserData) {
        Set<City> cities = cityProvider.getCityNamesFromPostalCode(newUserData.getPostalCode());

        if (cities.isEmpty()) {
            System.out.println("Podano nieistniejący kod pocztowy");
            getAndVerifyPostalCode(newUserData);
            return;
        }
        if (userDataValidator.hasMultipleCities(cities)) {
            newUserData.setCity(selectCity(cities));
        } else {
            newUserData.setCity(cities.stream().findFirst().get().getName());
        }
        System.out.println("Wybrano miasto: " + newUserData.getCity());
    }

    private String selectCity(Set<City> cities) {
        System.out.println("Dla danego kodu pocztowego znaleziono wiele miast, wybierz swoje miasto:");
        List<String> cityNames = cities.stream()
                .map(City::getName)
                .sorted()
                .collect(toList());
        DisplayUtil<String> displayUtil = new DisplayUtil<>();
        Map<Integer, String> mappedCities = displayUtil.convertToMap(cityNames);
        displayUtil.display(mappedCities);
        String selection = scanner.nextLine();
        if (displayUtil.canBeSelected(mappedCities, selection)) {
            return mappedCities.get(Integer.parseInt(selection));
        }
        System.out.println("Wybrano nieprawidłową wartości");
        return selectCity(cities);
    }

}
