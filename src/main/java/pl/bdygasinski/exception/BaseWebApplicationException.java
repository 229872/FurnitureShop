package pl.bdygasinski.exception;

import jakarta.ejb.ApplicationException;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationException(rollback = true)
public abstract class BaseWebApplicationException extends WebApplicationException {

    public BaseWebApplicationException(String message, Response.Status status) {
        super(message, createResponse(message, status));
    }

    private static Response createResponse(String message, Response.Status status) {
        JsonObject messageAsJson = Json.createObjectBuilder()
                .add("message", message)
                .build();


        return Response.status(status)
                .entity(messageAsJson)
                .build();
    }
}
