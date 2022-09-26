package com.books.view.validators;

import com.books.api.user.UserData;

public interface UserDataValidator {

    boolean isLoginValid(String login);
    boolean isEmailValid(String email);
    boolean isPostalCodeValid(String postalCode);

    void getAndVerifyLogin(UserData userData);
    void getAndVerifyPostalCode(UserData userData);

}
