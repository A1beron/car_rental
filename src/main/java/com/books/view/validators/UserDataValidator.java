package com.books.view.validators;

import com.books.api.user.UserData;

public interface UserDataValidator {

    void getAndVerifyEmail(UserData userData);
    void getAndVerifyLogin(UserData userData);
    void getAndVerifyPostalCode(UserData userData);

}
