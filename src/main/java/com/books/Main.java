package com.books;

import com.books.view.LoginView;
import com.books.view.View;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        //View createUser = new CreateUserViewImpl();
        //createUser.display();
        View login = new LoginView(Optional.empty());
        login.display();

    }
}

