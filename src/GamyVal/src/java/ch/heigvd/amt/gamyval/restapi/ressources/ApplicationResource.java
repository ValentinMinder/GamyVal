package ch.heigvd.amt.gamyval.restapi.ressources;

import ch.heigvd.amt.gamyval.models.entities.ApiKey;
import ch.heigvd.amt.gamyval.models.entities.Application;
import ch.heigvd.amt.gamyval.restapi.dtos.ApplicationNewDTO;
import ch.heigvd.amt.gamyval.restapi.dtos.ApplicationSummaryDTO;
import ch.heigvd.amt.gamyval.service.daos.ApplicationDAOLocal;
import ch.heigvd.amt.gamyval.service.daos.BusinessDomainEntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Resource and REST API entry point about Application entity.
 * 
 * Implemented:
 * POST: YES
 * GET collection: YES
 * GET item: YES
 * PUT item: YES
 * DELETE item: YES
 * 
 * @author Valentin Minder
 */
@Stateless
@Path("/application")
public class ApplicationResource {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;

    @EJB
    ApplicationDAOLocal applicationDAO;

    @Context
    HttpServletRequest request;
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ApplicationSummaryDTO create(ApplicationNewDTO dto) {
        Application application = new Application();

        application.setName(dto.getName());
        application.setDescr(dto.getDescr());
        ApiKey apiKey = new ApiKey();
        em.persist(apiKey);
        application.setApiKey(apiKey);

        applicationDAO.bindToAccount(dto.getEmail(), application);
        applicationDAO.create(application);

        return fromDAOtoDTO(application);
    }

    @PUT
    @Path("/{apiKey}")
    @Consumes("application/json")
    @Produces("application/json")
    public ApplicationSummaryDTO edit(@PathParam("apiKey") String apiKey, ApplicationNewDTO dto) throws BusinessDomainEntityNotFoundException {
        Application a = applicationDAO.getFromApiKey(apiKey);
        a.setName(dto.getName());
        a.setDescr(dto.getDescr());
        applicationDAO.update(a);
        return fromDAOtoDTO(a);
    }

    @DELETE
    @Path("/{apiKey}")
    public void remove(@PathParam("apiKey") String apiKey) throws BusinessDomainEntityNotFoundException {
        applicationDAO.delete(applicationDAO.getFromApiKey(apiKey));
    }

    @GET
    @Path("/{apiKey}")
    @Produces("application/json")
    public ApplicationSummaryDTO find(@PathParam("apiKey") String apiKey) {
        return fromDAOtoDTO(applicationDAO.getFromApiKey(apiKey));
    }

    @GET
    @Produces("application/json")
    public List<ApplicationSummaryDTO> findAll() {
        return fromDAOtoDTO(applicationDAO.findAll());
    }

    private ApplicationSummaryDTO fromDAOtoDTO(Application app) {
        ApplicationSummaryDTO a = new ApplicationSummaryDTO();
        a.setApiKey(app.getapiKey().getApiKey());
        a.setName(app.getName());
        a.setEmail(app.getAccount().getEmail());
        return a;
    }

    private List<ApplicationSummaryDTO> fromDAOtoDTO(List<Application> appList) {
        List<ApplicationSummaryDTO> listDTO = new ArrayList<>();
        for (Application app : appList) {
            listDTO.add(fromDAOtoDTO(app));
        }
        return listDTO;
    }
}
