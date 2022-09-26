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

    public boolean isLoginValid(String login) {
        return userLoginChecker.userLoginExist(login);
    }

    @Override
    public boolean isEmailValid(String email) {
        return EmailValidator.getInstance().isValid(userData.getEmail());
    }

    @Override
    public boolean isPostalCodeValid(String postalCode) {
        Pattern pattern = Pattern.compile(POSTAL_CODE_REGEX);
        return pattern.matcher(postalCode).matches();
    }

}
