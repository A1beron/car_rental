package com.books.dao.role;

import com.books.api.role.UserRole;
import com.books.model.Role;
import com.books.util.HibernateUtil;
import org.hibernate.Session;

public class RoleDao implements RoleProvider {

    @Override
    public Role getRole(UserRole userRole) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Role role = session.get(Role.class, userRole.getId());
        session.close();
        return role;
    }

}
