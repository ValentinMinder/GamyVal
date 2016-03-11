package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.Account;
import ch.heigvd.amt.gamyval.models.entities.Role;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class AccountDAO extends GenericDAO<Account, Long> implements AccountDAOLocal {

    @EJB
    RoleDAOLocal roleDAO;

    @Override
    public Account authenticate(String email, String password) {
        try {
            return (Account) em.createNamedQuery("Account.authenticate")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addRole(String email, String role) {
        Account account = getFromEmail(email);
        Role r = roleDAO.getFromNameCreate(role);
        account.addRole(r);
    }

    @Override
    public Account getFromEmail(String email) {
        try {
            return (Account) em.createNamedQuery("Account.getFromEmail")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
