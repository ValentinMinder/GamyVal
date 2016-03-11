package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.Rank;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RankDAOLocal extends IGenericDAO<Rank, Long> {

    Rank findGreaterThan(String apiKey, int score);
    Rank findSmallerThan(String apiKey, int score);
    List<Rank> findAllOrdered(String apiKey);
}
