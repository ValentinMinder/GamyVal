package ch.heigvd.amt.gamyval.models.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity (pojo) that represent an Account: this is the dev account using the
 * application.
 *
 * @author Valentin Minder
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Account.authenticate", query = "SELECT a FROM Account a WHERE a.email = :email AND a.password = :password"),
    @NamedQuery(name = "Account.getFromEmail", query = "SELECT a FROM Account a WHERE a.email = :email")
})
@XmlRootElement
public class Account extends AbstractDomainModelEntity<Long> {

    private String email;
    private String password;

    private String fName;
    private String lName;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public Account() {
        this.roles = new ArrayList<Role>();
    }

    public Account(String email, String fName, String lName, String password) {
        this.email = email;
        this.password = password;
        this.fName = fName;
        this.lName = lName;

        this.roles = new ArrayList<Role>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @XmlTransient
    public List<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
