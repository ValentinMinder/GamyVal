package ch.heigvd.amt.gamyval.restapi.dtos;

/**
 * DTO used to send information about account (output), without credentials and
 * private data (roles).
 *
 * @author Valentin Minder
 */
public class AccountSummaryDTO {

    private String email;
    private String fName;
    private String lName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
