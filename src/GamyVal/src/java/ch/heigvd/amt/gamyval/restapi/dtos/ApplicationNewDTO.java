package ch.heigvd.amt.gamyval.restapi.dtos;

/**
 * DTO used to create a new application (input).
 * 
 * @author Valentin Minder
 */
public class ApplicationNewDTO {

    private String email;
    private String name;
    private String descr;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

}
