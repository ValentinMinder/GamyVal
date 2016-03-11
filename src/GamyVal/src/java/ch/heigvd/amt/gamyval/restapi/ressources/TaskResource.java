package ch.heigvd.amt.gamyval.restapi.ressources;

import ch.heigvd.amt.gamyval.models.entities.Task;
import ch.heigvd.amt.gamyval.restapi.dtos.TaskNewDTO;
import ch.heigvd.amt.gamyval.service.daos.ApplicationDAOLocal;
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
 * Resource and REST API entry point about Task entity.
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
@Path("/task")
public class TaskResource {

    @Context
    HttpServletRequest request;

    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;

    @EJB
    ApplicationDAOLocal applicationDAO;

    @EJB
    TaskDAOLocal taskDAO;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Task create(TaskNewDTO dto) {
        String apiKey = (String) request.getParameter("apikey");
        Task task = new Task();

        task.setName(dto.getName());
        task.setScore(dto.getScore());

        applicationDAO.getFromApiKey(apiKey).addTask(task);
        taskDAO.create(task);

        return task;
    }
}
