package ch.heigvd.amt.gamyval.restapi.dtos;

/**
 * DTO used to create a new account (input).
 * 
 * @author Valentin Minder
 */
public class AccountNewDTO {

    private String email;
    private String password;

    private String fName;
    private String lName;
    private String[] roles;

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

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getlName() {
        return lName;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
