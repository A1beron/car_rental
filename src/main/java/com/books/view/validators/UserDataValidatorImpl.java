package com.books.view.validators;

import com.books.api.address.City;
import com.books.api.user.UserData;
import com.books.service.city.CityByPostalCodeProvider;
import com.books.service.user.UserLoginExistChecker;
import com.books.service.user.UserService;
import com.books.view.CityView;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

@AllArgsConstructor
public class UserDataValidatorImpl implements UserDataValidator {

    private static final String POSTAL_CODE_REGEX = "[0-9]{2}-[0-9]{3}";

    private final Scanner scanner;
    private final UserLoginExistChecker userLoginChecker;
    private final CityByPostalCodeProvider cityProvider;

    public UserDataValidatorImpl(Scanner scanner) {
        this.scanner = scanner;
        userLoginChecker = new UserService();
    }

    @Override
    public void getAndVerifyEmail(UserData userData) {
        System.out.println("Podaj email");
        String email = scanner.nextLine();
        userData.setEmail(email);
        if (!EmailValidator.getInstance().isValid(email)) {
            System.out.println("Niepoprawny format email");
            getAndVerifyEmail(userData);
        }
    }

    @Override
    public void getAndVerifyLogin(UserData userData) {
        System.out.println("Podaj login nowego użytkownika");
        String login = scanner.nextLine();
        userData.setLogin(login);
        if (userLoginChecker.userLoginExist(login)) {
            System.out.println("Login użytkownika już istnieje");
            getAndVerifyLogin(userData);
        }
    }

    @Override
    public void getAndVerifyPostalCode(UserData userData) {
        System.out.println("Podaj kod pocztowy");
        String postalCode = scanner.nextLine();
        userData.setPostalCode(postalCode);
        Pattern pattern = Pattern.compile(POSTAL_CODE_REGEX);
        if (!pattern.matcher(postalCode).matches()) {
            System.out.println("Niepoprawny format kodu pocztowego");
            getAndVerifyPostalCode(userData);
        }

    }

    private String getAndVerifyCity(String postalCode) {
        Set<City> cities = cityProvider.getCityNamesFromPostalCode(postalCode);
        if (cities.size() > 1) {

        }
        if (cities.isEmpty()) {
            return scanner.nextLine();
        }
        return cities.stream().findFirst().get().getName();
    }

}
