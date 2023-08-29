package pl.bdygasinski.exception.account.state;

import jakarta.ws.rs.core.Response;
import pl.bdygasinski.exception.BaseWebApplicationException;

public class CantUnblockNotBlockedAccountException extends BaseWebApplicationException {
    public CantUnblockNotBlockedAccountException(String message, Response.Status status) {
        super(message, status);
    }
}
