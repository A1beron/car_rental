package com.books.view;

import com.books.model.Book;

public class BooksView implements View<Book> {
    @Override
    public void display() {
        System.out.println("BooksView");
    }

    @Override
    public Book getData() {
        return null;
    }
}
