package com.books.dao;

public interface EntityLoader<T> {

    T createEntity(T entity);

}
