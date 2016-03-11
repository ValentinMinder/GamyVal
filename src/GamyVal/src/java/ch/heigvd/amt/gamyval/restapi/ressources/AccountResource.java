package ch.heigvd.amt.gamyval.restapi.ressources;

import ch.heigvd.amt.gamyval.models.entities.Account;
import ch.heigvd.amt.gamyval.restapi.dtos.AccountNewDTO;
import ch.heigvd.amt.gamyval.restapi.dtos.AccountSummaryDTO;
import ch.heigvd.amt.gamyval.service.daos.AccountDAOLocal;
import ch.heigvd.amt.gamyval.service.daos.BusinessDomainEntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Resource and REST API entry point about Account entity.
 * 
 * Implemented:
 * POST: YES
 * GET collection: YES
 * GET INSECURED collection: YES (/insecure)
 * GET item: YES
 * PUT item: NO
 * DELETE item: NO
 * 
 * This allows also to see the security flaws when not using DTO (credential leakage).
 * 
 * @author Valentin Minder
 */
@Stateless
@Path("/account")
public class AccountResource {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;

    @EJB
    AccountDAOLocal accountDAO;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public AccountSummaryDTO create(AccountNewDTO dto) {
        Account acc = new Account();
        acc.setEmail(dto.getEmail());
        acc.setPassword(dto.getPassword());
        acc.setlName(dto.getlName());
        acc.setfName(dto.getfName());
        accountDAO.create(acc);

        String[] roles = dto.getRoles();
        if (roles != null) {
            for (String role : roles) {
                accountDAO.addRole(acc.getEmail(), role);
            }
        }
        return fromDAOtoDTO(acc);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public AccountSummaryDTO find(@PathParam("id") Long id) throws BusinessDomainEntityNotFoundException {
        return fromDAOtoDTO(accountDAO.findById(id));
    }

    // correct GET with transformation Entity -> DTO
    @GET
    @Produces("application/json")
    public List<AccountSummaryDTO> findALL() {
        return fromDAOtoDTO(accountDAO.findAll());
    }

    // INSECURE GET with credentials!
    @GET
    @Produces("application/json")
    @Path("/insecure")
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    private AccountSummaryDTO fromDAOtoDTO(Account acc) {
        AccountSummaryDTO a = new AccountSummaryDTO();
        a.setEmail(acc.getEmail());
        a.setfName(acc.getfName());
        a.setlName(acc.getlName());
        return a;
    }

    private List<AccountSummaryDTO> fromDAOtoDTO(List<Account> accList) {
        List<AccountSummaryDTO> listDTO = new ArrayList<>();
        for (Account acc : accList) {
            listDTO.add(fromDAOtoDTO(acc));
        }
        return listDTO;
    }

}
