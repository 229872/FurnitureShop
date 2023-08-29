package pl.bdygasinski.exception;

import jakarta.ws.rs.core.Response;
import pl.bdygasinski.exception.account.conflict.EmailConflictException;
import pl.bdygasinski.exception.account.conflict.LoginConflictException;
import pl.bdygasinski.exception.account.find.AccountNotFoundException;

import static jakarta.ws.rs.core.Response.Status;
import static jakarta.ws.rs.core.Response.Status.*;

public class ExceptionFactory {

    public static UnknownException createUnknownException() {
        return new UnknownException(ExceptionMessage.UNKNOWN, INTERNAL_SERVER_ERROR);
    }

    public static LoginConflictException createLoginConflictException() {
        return new LoginConflictException(ExceptionMessage.LOGIN_CONFLICT, CONFLICT);
    }

    public static EmailConflictException createEmailConflictException() {
        return new EmailConflictException(ExceptionMessage.EMAIL_CONFLICT, CONFLICT);
    }

    public static AccountNotFoundException createAccountNotFoundException() {
        return new AccountNotFoundException(ExceptionMessage.ACCOUNT_NOT_FOUND, NOT_FOUND);
    }
}
