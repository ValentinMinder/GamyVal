package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.Role;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class RoleDAO extends GenericDAO<Role, Long> implements RoleDAOLocal {

    @Override
    public Role getFromNameCreate(String name) {
        Role r = getFromName(name);
        if (null == r) {
            r = new Role();
            r.setRole(name);
            create(r);
        }
        return r;
    }

    @Override
    public Role getFromName(String name) {
        try {
            return (Role) em.createNamedQuery("Role.getFromName")
                    .setParameter("role", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
