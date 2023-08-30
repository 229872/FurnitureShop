package pl.bdygasinski.exception.account.find;

import jakarta.ws.rs.core.Response;
import pl.bdygasinski.exception.BaseWebApplicationException;

public class AccountNotFoundException extends BaseWebApplicationException {
    public AccountNotFoundException(String message, Response.Status status) {
        super(message, status);
    }
}
