package com.books.controller.user;

import com.books.api.user.UserData;
import com.books.dao.role.RoleDao;
import com.books.dao.role.RoleProvider;
import com.books.dao.user.UserDao;
import com.books.model.Address;
import com.books.model.User;
import com.books.service.city.CityByPostalCodeProvider;
import com.books.service.city.CityService;
import com.books.view.TestView;
import com.books.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.books.api.role.Roles.USER;

@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserDao userDao;
    private final RoleProvider roleProvider;
    private final CityByPostalCodeProvider cityByPostalCodeProvider;


    public UserControllerImpl() {
        userDao = new UserDao();
        roleProvider = new RoleDao();
        cityByPostalCodeProvider = new CityService();
    }

    @Override
    public View createUser(UserData userData) {

        userDao.createUser(mapToUser(userData));
        return new TestView();
    }

    private Address mapToAddress(UserData userData) {
        return new Address(
                userData.getStreet(),
                userData.getBuildingNo(),
                userData.getApartmentNo(),
                userData.getCity(),
                userData.getPostalCode()
        );
    }

    private User mapToUser(UserData userData) {
        return new User(
            userData.getLogin(),
                userData.getEmail(),
                userData.getPassword(),
                userData.getFirstName(),
                userData.getLastName(),
                List.of(roleProvider.getRole(USER)),
                mapToAddress(userData)
        );
    }

}
