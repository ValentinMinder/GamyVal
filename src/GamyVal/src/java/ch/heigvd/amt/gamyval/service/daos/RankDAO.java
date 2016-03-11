package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.Rank;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
public class RankDAO extends GenericDAO<Rank, Long> implements RankDAOLocal {

    @EJB
    ApplicationDAOLocal applicationDAO;

    @Override
    public Rank findGreaterThan(String apiKey, int score) {
        try {
            return (Rank) em.createNamedQuery("Rank.findGreaterThan")
                    .setParameter("score", score)
                    .setParameter("apiKey", apiKey)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Rank findSmallerThan(String apiKey, int score) {
        try {
            return (Rank) em.createNamedQuery("Rank.findSmallerThan")
                    .setParameter("score", score)
                    .setParameter("apiKey", apiKey)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Rank> findAllOrdered(String apiKey) {
        List<Rank> result = new ArrayList<>();

        Query q = em.createNamedQuery("Rank.findAllOrdered")
                .setParameter("apiKey", apiKey);
        result.addAll(q.getResultList());
        return result;
    }
}
