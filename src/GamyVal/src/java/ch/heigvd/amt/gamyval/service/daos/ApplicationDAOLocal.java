package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.ApiKey;
import ch.heigvd.amt.gamyval.models.entities.Application;
import ch.heigvd.amt.gamyval.models.entities.Rank;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ApplicationDAOLocal extends IGenericDAO<Application, Long> {

    public void bindToAccount(String email, Application application);

    public Application getFromApiKey(String apiKey);

    public List<Rank> getRanksList(String apiKey);

    public ApiKey generateAPIKEY();
}
