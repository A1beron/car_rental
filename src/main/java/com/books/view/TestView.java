package com.books.view;

public class TestView implements View {
    @Override
    public void display() {
        System.out.println("user created");
    }

    @Override
    public Object getData() {
        return null;
    }
}
