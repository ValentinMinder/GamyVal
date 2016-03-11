package ch.heigvd.amt.gamyval.models.entities;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity (pojo) that represent an ApiKey: this stores a key that identify and
 * authenticate an app and that developers use to make request to the Rest Api.
 *
 * @author Valentin Minder
 */
@Entity
@XmlRootElement
public class ApiKey extends AbstractDomainModelEntity<Long> {

    private String apiKey;

    public ApiKey() {
        apiKey = generateDevID();
    }

    public ApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
