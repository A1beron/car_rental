package com.books.dao;

import org.hibernate.Session;

import static com.books.util.HibernateUtil.getSessionFactory;

public class EntityDao<T> implements EntityLoader<T> {

    @Override
    public T createEntity(T entity) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(entity);
        session.flush();
        session.close();
        return entity;
    }

}
