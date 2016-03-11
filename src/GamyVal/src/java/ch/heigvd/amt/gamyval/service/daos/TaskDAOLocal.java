package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.Task;
import javax.ejb.Local;

@Local
public interface TaskDAOLocal extends IGenericDAO<Task, Long> {

    Task getFromID(String apiKey, String taskID);
}
