package ch.heigvd.amt.gamyval.restapi.dtos;

/**
 * DTO used to send information about application (output), without all the
 * details and the structure, only a few information.
 *
 * @author Valentin Minder
 */
public class ApplicationSummaryDTO {

    private String email;
    private String name;
    private String apiKey;

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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
