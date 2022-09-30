package com.books.controller.user;

import com.books.api.user.NewUserData;
import com.books.view.View;

public interface CreateUserController {

    View createUser(NewUserData newUserData);

}
