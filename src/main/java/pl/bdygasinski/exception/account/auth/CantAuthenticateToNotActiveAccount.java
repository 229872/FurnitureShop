package pl.bdygasinski.exception.account.auth;

import jakarta.ws.rs.core.Response;
import pl.bdygasinski.exception.BaseWebApplicationException;

public class CantAuthenticateToNotActiveAccount extends BaseWebApplicationException {
    public CantAuthenticateToNotActiveAccount(String message, Response.Status status) {
        super(message, status);
    }
}
