package pl.bdygasinski.exception.account.auth;

import jakarta.ws.rs.core.Response;
import pl.bdygasinski.exception.BaseWebApplicationException;

public class InvalidCredentialsException extends BaseWebApplicationException {
    public InvalidCredentialsException(String message, Response.Status status) {
        super(message, status);
    }
}
