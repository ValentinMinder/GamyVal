package ch.heigvd.amt.gamyval.restapi.ressources;

import ch.heigvd.amt.gamyval.models.entities.Event;
import ch.heigvd.amt.gamyval.models.entities.EndUser;
import ch.heigvd.amt.gamyval.models.entities.Task;
import ch.heigvd.amt.gamyval.service.daos.EndUserDAOLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import ch.heigvd.amt.gamyval.service.daos.TaskDAOLocal;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Resource and REST API entry point about Event entity.
 * 
 * Implemented:
 * POST: YES
 * GET collection: NO
 * GET item: NO
 * PUT item: NO
 * DELETE item: NO
 * 
 * @author Valentin Minder
 */
@Stateless
@Path("/event")
public class EventResource {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;

    @Context
    HttpServletRequest request;

    @EJB
    EndUserDAOLocal endUserDAO;

    @EJB
    TaskDAOLocal taskDAO;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Event create() {
        String apiKey = (String) request.getParameter("apikey");
        String userID = (String) request.getParameter("userID");
        String taskID = (String) request.getParameter("taskID");

        EndUser endUser = endUserDAO.getByID(apiKey, userID);
        Task task = taskDAO.getFromID(apiKey, taskID);

        Event event = new Event();
        em.persist(event);
        event.setTask(task);
        endUser.addEvent(event);

        return event;
    }

}
