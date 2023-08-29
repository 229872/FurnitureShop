package pl.bdygasinski.businesslogic.service.api;

import jakarta.ejb.Local;
import pl.bdygasinski.businesslogic.model.AccountDataForUpdate;
import pl.bdygasinski.dataaccess.entity.Account;

import java.util.List;

@Local
public interface AccountService {
    Account create(Account account);
    Account update(Long id, AccountDataForUpdate updateData);
    Account block(Long id);
    Account unblock(Long id);
    Account archive(Long id);
    Account findById(Long id);
    Account findByLogin(String login);
    Account findByEmail(String email);
    List<Account> findAll();
}
