package ch.heigvd.amt.gamyval.restapi.ressources;

import ch.heigvd.amt.gamyval.models.entities.Application;
import ch.heigvd.amt.gamyval.models.entities.EndUser;
import ch.heigvd.amt.gamyval.models.entities.Rank;
import ch.heigvd.amt.gamyval.restapi.dtos.EndUserSummaryDTO;
import ch.heigvd.amt.gamyval.service.daos.ApplicationDAOLocal;
import ch.heigvd.amt.gamyval.service.daos.EndUserDAOLocal;
import ch.heigvd.amt.gamyval.service.daos.RankDAOLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import ch.heigvd.amt.gamyval.service.daos.TaskDAOLocal;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 * Resource and REST API entry point about EndUser entity.
 * 
 * Implemented:
 * POST: YES
 * GET collection: YES (to security flaw only)
 * GET item: NO
 * PUT item: NO
 * DELETE item: NO
 * 
 * GET /details: YES - all the statistics about the user
 * 
 * @author Valentin Minder
 */
@Stateless
@Path("/enduser")
public class EndUserResource {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;

    @Context
    HttpServletRequest request;

    @EJB
    ApplicationDAOLocal applicationDAO;

    @EJB
    EndUserDAOLocal endUserDAO;

    @EJB
    RankDAOLocal rankDAO;

    @EJB
    TaskDAOLocal eventDAO;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public EndUser create() {
        String apiKey = (String) request.getParameter("apikey");
        Application app = applicationDAO.getFromApiKey(apiKey);
        EndUser endUser = new EndUser();
        em.persist(endUser);
        app.addEndUser(endUser);
        return endUser;
    }

    @GET
    @Path("/details")
    @Produces("application/json")
    public EndUserSummaryDTO details() {
        String apiKey = (String) request.getParameter("apikey");
        String userID = (String) request.getParameter("userID");

        EndUserSummaryDTO result = new EndUserSummaryDTO();
        result.setUserID(userID);

        int scoreUser = endUserDAO.calculateScore(apiKey, userID);
        result.setScore(scoreUser);

        Rank rank = rankDAO.findSmallerThan(apiKey, scoreUser);
        if (rank != null) {
            result.setRank(rank.getName());
        } else {
            // rank is left null!
            // result.setRank("No rank reached, sorry!");
        }

        Rank next = rankDAO.findGreaterThan(apiKey, scoreUser);
        if (next != null) {
            result.setNextRank(next.getName());
            result.setNextRankMissing(next.getScore() - scoreUser);
        } else {
            // could be left null to indicate their is no further rank
            result.setNextRank("No next rank! Max reached!");
            result.setNextRankMissing(0);
        }

        return result;
    }

    @GET
    @Produces("application/json")
    public List<EndUser> findAll() {
        // ALL!!! to demonstrate security issue
        return endUserDAO.findAll();
    }

}
