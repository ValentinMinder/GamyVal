package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.Task;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class TaskDAO extends GenericDAO<Task, Long> implements TaskDAOLocal {

    @Override
    public Task getFromID(String apiKey, String taskID) {
        try {
            return (Task) em.createNamedQuery("Task.getFromID")
                    .setParameter("taskID", taskID)
                    .setParameter("apiKey", apiKey)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
