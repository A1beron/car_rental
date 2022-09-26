package com.books.view.validators;

import com.books.api.address.City;
import com.books.service.user.UserLoginExistChecker;
import com.books.service.user.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Set;
import java.util.regex.Pattern;

@AllArgsConstructor
public class UserDataValidatorImpl implements UserDataValidator {

    private static final String POSTAL_CODE_REGEX = "[0-9]{2}-[0-9]{3}";

    private final UserLoginExistChecker userLoginChecker;

    public UserDataValidatorImpl() {
        userLoginChecker = new UserService();
    }

    public boolean isLoginValid(String login) {
        return !userLoginChecker.userLoginExist(login);
    }

    @Override
    public boolean isEmailValid(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    @Override
    public boolean isPostalCodeValid(String postalCode) {
        Pattern pattern = Pattern.compile(POSTAL_CODE_REGEX);
        return pattern.matcher(postalCode).matches();
    }

    @Override
    public boolean hasMultipleCities(Set<City> cities) {
        return cities.size() > 1;
    }

}
