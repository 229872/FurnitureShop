package pl.bdygasinski.exception;

import jakarta.ws.rs.core.Response;
import pl.bdygasinski.exception.account.auth.CantAuthenticateToNotActiveAccount;
import pl.bdygasinski.exception.account.auth.InvalidCredentialsException;
import pl.bdygasinski.exception.account.conflict.EmailConflictException;
import pl.bdygasinski.exception.account.conflict.LoginConflictException;
import pl.bdygasinski.exception.account.find.AccountNotFoundException;
import pl.bdygasinski.exception.account.state.CantBlockNotActiveAccountException;
import pl.bdygasinski.exception.account.state.CantUnblockNotBlockedAccountException;

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

    public static CantBlockNotActiveAccountException createCantBlockNotActiveAccountException() {
        return new CantBlockNotActiveAccountException(ExceptionMessage.CANT_BLOCK_NOT_ACTIVE_ACCOUNT, BAD_REQUEST);
    }

    public static CantUnblockNotBlockedAccountException createCantUnblockNotBlockedAccountException() {
        return new CantUnblockNotBlockedAccountException(ExceptionMessage.CANT_UNBLOCK_NOT_BLOCKED_ACCOUNT, BAD_REQUEST);
    }

    public static InvalidCredentialsException createInvalidCredentialsException() {
        return new InvalidCredentialsException(ExceptionMessage.INVALID_CREDENTIALS_EXCEPTION, UNAUTHORIZED);
    }

    public static CantAuthenticateToNotActiveAccount createCantAuthenticateToNotActiveAccount() {
        return new CantAuthenticateToNotActiveAccount(ExceptionMessage.CANT_AUTH_TO_NOT_ACTIVE_ACCOUNT, UNAUTHORIZED);
    }
}
