package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.Role;
import javax.ejb.Local;

@Local
public interface RoleDAOLocal extends IGenericDAO<Role, Long> {

    public Role getFromNameCreate(String name);

    Role getFromName(String name);

}
