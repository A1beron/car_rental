package com.books.dao.role;

import com.books.api.role.Roles;
import com.books.model.Role;
import com.books.util.HibernateUtil;
import org.hibernate.Session;

public class RoleDao implements RoleProvider {

    @Override
    public Role getRole(Roles roles) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Role role = session.get(Role.class, roles.getId());
        session.close();
        return role;
    }

}
