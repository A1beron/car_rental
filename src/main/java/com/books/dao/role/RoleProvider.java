package com.books.dao.role;

import com.books.api.role.UserRole;
import com.books.model.Role;

public interface RoleProvider {

    Role getRole(UserRole userRole);

}
