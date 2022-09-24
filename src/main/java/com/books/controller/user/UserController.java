package com.books.controller.user;

import com.books.api.user.UserData;
import com.books.view.View;

public interface UserController {

    View createUser(UserData userData);

}
