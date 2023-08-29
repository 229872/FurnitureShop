package pl.bdygasinski.exception.account.conflict;

import jakarta.ws.rs.core.Response;
import pl.bdygasinski.exception.BaseWebApplicationException;

public class LoginConflictException extends BaseWebApplicationException {
    public LoginConflictException(String message, Response.Status status) {
        super(message, status);
    }
}
