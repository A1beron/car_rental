package com.books.dao.role;

import com.books.api.role.Roles;
import com.books.model.Role;

public interface RoleProvider {

    Role getRole(Roles roles);

}
