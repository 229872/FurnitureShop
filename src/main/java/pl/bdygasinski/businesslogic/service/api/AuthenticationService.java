package pl.bdygasinski.businesslogic.service.api;

import jakarta.ejb.Local;
import jakarta.json.JsonObject;
import pl.bdygasinski.businesslogic.model.Credentials;
@Local
public interface AuthenticationService {
    JsonObject authenticate(Credentials credentials);
}
