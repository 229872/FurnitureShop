package pl.bdygasinski.dataaccess.repository.api;

import jakarta.ejb.Local;
import pl.bdygasinski.dataaccess.entity.Account;

import java.util.Optional;

@Local
public interface AccountRepository extends Repository<Account> {
    Optional<Account> findByLogin(String login);
    Optional<Account> findByEmail(String email);
}
