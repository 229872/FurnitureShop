package pl.bdygasinski.exception;

public class ExceptionMessage {

    public static final String UNKNOWN = "exception.unknown";
    public static final String LOGIN_CONFLICT = "exception.account.conflict.login";
    public static final String EMAIL_CONFLICT = "exception.account.conflict.email";
    public static final String ACCOUNT_NOT_FOUND = "exception.account.find.notfound";
    public static final String CANT_BLOCK_NOT_ACTIVE_ACCOUNT = "exception.account.state.block.notactive";
    public static final String CANT_UNBLOCK_NOT_BLOCKED_ACCOUNT = "exception.account.state.unblock.notblocked";
}
