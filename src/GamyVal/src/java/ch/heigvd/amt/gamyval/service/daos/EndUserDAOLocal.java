package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.EndUser;
import javax.ejb.Local;

@Local
public interface EndUserDAOLocal extends IGenericDAO<EndUser, Long> {

    public int calculateScore(String apiKey, String userID);

    public EndUser getByID(String apiKey, String userID);

    public int countLast30Days();
}
