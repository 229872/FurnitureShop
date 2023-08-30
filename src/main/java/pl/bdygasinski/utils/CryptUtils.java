package pl.bdygasinski.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CryptUtils {

    static String hashPassword(String password, int cost) {
        return BCrypt.withDefaults().hashToString(cost, password.toCharArray());
    }

    public static boolean verify(String password, String hashedPassword) {
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword.toCharArray()).verified;
    }
}
