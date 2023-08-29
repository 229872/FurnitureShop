package pl.bdygasinski.exception.account.conflict;

import jakarta.ws.rs.core.Response;
import pl.bdygasinski.exception.BaseWebApplicationException;

public class EmailConflictException extends BaseWebApplicationException {
    public EmailConflictException(String message, Response.Status status) {
        super(message, status);
    }
}
