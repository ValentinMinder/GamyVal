package ch.heigvd.amt.gamyval.restapi.ressources;

import ch.heigvd.amt.gamyval.models.entities.Application;
import ch.heigvd.amt.gamyval.models.entities.Rank;
import ch.heigvd.amt.gamyval.restapi.dtos.RankNewDTO;
import ch.heigvd.amt.gamyval.service.daos.ApplicationDAOLocal;
import ch.heigvd.amt.gamyval.service.daos.RankDAOLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Resource and REST API entry point about Rank entity.
 * 
 * Implemented:
 * POST: YES
 * GET collection: YES (not ordered)
 * GET item: NO
 * PUT item: NO
 * DELETE item: NO
 * 
 * GET /ordered collection: to demonstrate the power of named queries and ordered items.
 * 
 * @author Valentin Minder
 */
@Stateless
@Path("/rank")
public class RankRessource {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;

    @Context
    HttpServletRequest request;

    @EJB
    ApplicationDAOLocal applicationDAO;

    @EJB
    RankDAOLocal rankDAO;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Rank create(RankNewDTO dto) {

        Rank rank = new Rank();

        rank.setName(dto.getName());
        rank.setScore(dto.getScore());
        String apikey;
        // from the http header
        apikey = (String) request.getHeader("apikey");

        // from the parameters ?apikey=...
        apikey = (String) request.getParameter("apikey");

        Application app = applicationDAO.getFromApiKey(apikey);

        app.addRank(rank);

        rankDAO.create(rank);
        
        return rank;
    }
    
    // ranks ordered
    @GET
    @Path("/ordered")
    @Produces("application/json")
    public List<Rank> findAllOrdered() {
        String apikey = (String) request.getParameter("apikey");
        return rankDAO.findAllOrdered(apikey);
    }
    
    // ranks not ordered
    @GET
    @Produces("application/json")
    public List<Rank> findAll() {
        // allows ranks from every app, but the demonstration is about the order
        return rankDAO.findAll();
    }

}
