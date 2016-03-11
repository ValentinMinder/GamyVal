package ch.heigvd.amt.gamyval.models.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity (pojo) that represent an Event: when a user does a task (the user X
 * did task Y at time T).
 *
 * @author Valentin Minder
 */
@Entity
@XmlRootElement
public class Event extends AbstractDomainModelEntity<Long> {

    @ManyToOne
    private Task task;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timer;

    public Event() {
        timer = new Date();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getTimer() {
        return timer;
    }
}
