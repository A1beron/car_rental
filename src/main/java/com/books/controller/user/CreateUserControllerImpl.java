package com.books.controller.user;

import com.books.api.user.NewUserData;
import com.books.dao.role.RoleDao;
import com.books.dao.role.RoleProvider;
import com.books.dao.user.UserDao;
import com.books.model.Address;
import com.books.model.User;
import com.books.view.TestView;
import com.books.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.books.api.role.UserRole.USER;

@AllArgsConstructor
public class CreateUserControllerImpl implements CreateUserController {

    private final UserDao userDao;
    private final RoleProvider roleProvider;


    public CreateUserControllerImpl() {
        userDao = new UserDao();
        roleProvider = new RoleDao();
    }

    @Override
    public View createUser(NewUserData newUserData) {
        userDao.createUser(mapToUser(newUserData));
        return new TestView();
    }

    private Address mapToAddress(NewUserData newUserData) {
        return new Address(
                newUserData.getStreet(),
                newUserData.getBuildingNo(),
                newUserData.getApartmentNo(),
                newUserData.getCity(),
                newUserData.getPostalCode()
        );
    }

    private User mapToUser(NewUserData newUserData) {
        return new User(
            newUserData.getLogin(),
                newUserData.getEmail(),
                newUserData.getPassword(),
                newUserData.getFirstName(),
                newUserData.getLastName(),
                List.of(roleProvider.getRole(USER)),
                mapToAddress(newUserData)
        );
    }

}
