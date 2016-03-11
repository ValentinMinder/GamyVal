package ch.heigvd.amt.gamyval.models.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity (pojo) that represent an Role: each Account might be assigned several
 * Roles.
 *
 * @author Valentin Minder
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Role.getFromName", query = "SELECT r FROM Role r WHERE r.role = :role")
})
@XmlRootElement
public class Role extends AbstractDomainModelEntity<Long> {

    private String role;

    public Role() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
