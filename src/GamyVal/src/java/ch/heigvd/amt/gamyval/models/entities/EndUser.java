package ch.heigvd.amt.gamyval.models.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity (pojo) that represent an EndUser: this is the users that dev of the
 * third-party app will create. They will only identifier by an ID.
 *
 * @author Valentin Minder
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EndUser.getByID", query = "SELECT e FROM Application a INNER JOIN a.apiKey ak INNER JOIN a.endUsers e WHERE ak.apiKey = :apiKey AND e.userID = :userID")
})
public class EndUser extends AbstractDomainModelEntity<Long> {

    final private String userID;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Event> events;

    @Temporal(TemporalType.TIMESTAMP)
    final private Date timer;

    public EndUser() {
        timer = new Date();
        userID = generateDevID();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public String getUserID() {
        return userID;
    }

    public Date getTimer() {
        return timer;
    }
}
