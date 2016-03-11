package ch.heigvd.amt.gamyval.models.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity (pojo) that represent an Application: this is the app that dev of the
 * third-party develop and the entry-point to all the data related to a single
 * product.
 *
 * @author Valentin Minder
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Application.getFromApiKey", query = "SELECT a FROM Application a INNER JOIN a.apiKey ak WHERE ak.apiKey = :apiKey")
})
public class Application extends AbstractDomainModelEntity<Long> {

    private String name;
    private String descr;
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @OneToOne(fetch = FetchType.EAGER)
    private ApiKey apiKey;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Rank> ranks;

    @OneToMany(fetch = FetchType.LAZY)
    private List<EndUser> endUsers;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Task> tasks;

    public Application() {
        this.endUsers = new ArrayList<EndUser>();
        this.tasks = new ArrayList<Task>();
        this.ranks = new ArrayList<Rank>();
    }

    public Application(Account account, ApiKey apiKey, String name, String descr, boolean active) {
        this.account = account;
        this.apiKey = apiKey;
        this.name = name;
        this.descr = descr;
        this.active = active;
        this.endUsers = new ArrayList<EndUser>();
        this.tasks = new ArrayList<Task>();
        this.ranks = new ArrayList<Rank>();

    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApiKey getapiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    public List<EndUser> getEndUsers() {
        return endUsers;
    }

    public void addEndUser(EndUser eu) {
        endUsers.add(eu);
    }

    @XmlTransient
    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @XmlTransient
    public List<Rank> getRanks() {
        return ranks;
    }

    public void addRank(Rank rank) {
        ranks.add(rank);
    }
}
