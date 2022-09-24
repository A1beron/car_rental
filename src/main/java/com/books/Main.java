package com.books;

import com.books.view.CreateUserViewImpl;
import com.books.view.View;

public class Main {

    public static void main(String[] args) {

        View createUser = new CreateUserViewImpl();
        createUser.display();

    }
}

