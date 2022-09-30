package com.books.view.validators;

import com.books.api.address.City;

import java.util.Set;

public interface UserDataValidator {

    boolean isLoginValid(String login);
    boolean isEmailValid(String email);
    boolean isPostalCodeValid(String postalCode);
    boolean hasMultipleCities(Set<City> cities);

}
