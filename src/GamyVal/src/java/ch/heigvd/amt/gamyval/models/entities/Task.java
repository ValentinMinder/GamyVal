package ch.heigvd.amt.gamyval.models.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity (pojo) that represent a Task: the dev of the third-party app will
 * define task that will give their user some points (eg: question gives 5
 * points, an upvote gives 1 point)
 *
 * @author Valentin Minder
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.getFromID", query = "SELECT t FROM Application a INNER JOIN a.apiKey ak INNER JOIN a.tasks t WHERE ak.apiKey = :apiKey AND t.taskID = :taskID")
})
public class Task extends AbstractDomainModelEntity<Long> {

    private String name;
    private String taskID;
    private long score;

    public Task() {
        taskID = generateDevID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

}
