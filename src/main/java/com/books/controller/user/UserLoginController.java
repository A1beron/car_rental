package com.books.controller.user;

import com.books.api.user.UserLoginData;
import com.books.view.View;

public interface UserLoginController {

    View login(UserLoginData userLoginData);

}
