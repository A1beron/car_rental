package com.books.dao.user;

import com.books.dao.EntityDao;
import com.books.dao.EntityLoader;
import com.books.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

import java.util.Optional;

import static com.books.util.HibernateUtil.getSessionFactory;

@AllArgsConstructor
public class UserDao implements UserLoader, UserProvider {

    private final EntityLoader<User> userEntityLoader;

    public UserDao() {
        userEntityLoader = new EntityDao<>();
    }

    @Override
    public User createUser(User user) {
        return userEntityLoader.createEntity(user);
    }

    public Optional<User> findUserByLogin(String login) {
        Session session = getSessionFactory().openSession();
        Optional<User> user = session.createQuery("select u from User u join fetch u.roles where u.login=:login ", User.class)
                .setParameter("login", login)
                .stream()
                .findAny();
        session.close();
        return user;
    }

}
