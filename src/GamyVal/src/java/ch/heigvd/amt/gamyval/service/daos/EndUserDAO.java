package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.EndUser;
import ch.heigvd.amt.gamyval.models.entities.Event;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class EndUserDAO extends GenericDAO<EndUser, Long> implements EndUserDAOLocal {

    @Override
    public int calculateScore(String apiKey, String userID) {
        EndUser eu = this.getByID(apiKey, userID);

        int scoreUser = 0;
        for (Event a : eu.getEvents()) {
            scoreUser += a.getTask().getScore();
        }
        return scoreUser;
    }

    @Override
    public EndUser getByID(String apiKey, String userID) {
        return (EndUser) em.createNamedQuery("EndUser.getByID")
                .setParameter("apiKey", apiKey)
                .setParameter("userID", userID)
                .getSingleResult();
    }

    @Override
    public int countLast30Days() {
        int last = 0;
        Date back30days = new Date();
        // set date to 30 days before
        long shift = 30 * 24 * 3600 * 1000L;
        back30days.setTime(back30days.getTime() - shift);
        List<EndUser> list = this.findAll();
        for (EndUser eu : list) {
            if (eu.getTimer().after(back30days)) {
                last++;
            }
        }
        return last;
    }

}
