package pl.bdygasinski.exception;

import jakarta.ws.rs.core.Response;

public class UnknownException extends BaseWebApplicationException {
    public UnknownException(String message, Response.Status status) {
        super(message, status);
    }
}
