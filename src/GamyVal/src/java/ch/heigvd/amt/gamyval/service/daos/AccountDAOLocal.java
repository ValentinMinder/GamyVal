package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.Account;
import javax.ejb.Local;

@Local
public interface AccountDAOLocal extends IGenericDAO<Account, Long> {

    Account authenticate(String email, String password);

    Account getFromEmail(String email);

    void addRole(String email, String role);
}
