package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.ApiKey;
import ch.heigvd.amt.gamyval.models.entities.Application;
import ch.heigvd.amt.gamyval.models.entities.Rank;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ApplicationDAO extends GenericDAO<Application, Long> implements ApplicationDAOLocal {

    @EJB
    AccountDAOLocal accountDAO;
    
    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;

    @Override
    public void bindToAccount(String email, Application application) {
        application.setAccount(accountDAO.getFromEmail(email));
    }

    @Override
    public Application getFromApiKey(String apiKey) {
        return (Application) em.createNamedQuery("Application.getFromApiKey")
                .setParameter("apiKey", apiKey)
                .getSingleResult();
    }

    @Override
    public List<Rank> getRanksList(String apiKey) {
        List<Rank> ranks = new ArrayList<Rank>();
        for (Rank r : getFromApiKey(apiKey).getRanks()) {
            ranks.add(r);
        }
        return ranks;
    }

    /**
     * Create a persistant api key.
     */
    @Override
    public ApiKey generateAPIKEY() {
        ApiKey apiKey = new ApiKey();
        em.persist(apiKey);
        return apiKey;
    }
}
