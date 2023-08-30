package pl.bdygasinski.businesslogic.service.impl;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import pl.bdygasinski.businesslogic.model.Credentials;
import pl.bdygasinski.businesslogic.service.AbstractService;
import pl.bdygasinski.businesslogic.service.api.AuthenticationService;
import pl.bdygasinski.dataaccess.entity.Account;
import pl.bdygasinski.dataaccess.entity.AccountMetaData;
import pl.bdygasinski.dataaccess.entity.enums.AccountState;
import pl.bdygasinski.dataaccess.repository.api.AccountRepository;
import pl.bdygasinski.exception.ExceptionFactory;
import pl.bdygasinski.utils.CryptUtils;
import pl.bdygasinski.utils.TokenUtils;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AuthenticationServiceImpl extends AbstractService implements AuthenticationService {

    @Inject
    private AccountRepository accountRepository;

    @Override
    public JsonObject authenticate(Credentials credentials) {
        Account account = accountRepository.findByLogin(credentials.login())
                .orElseThrow(ExceptionFactory::createInvalidCredentialsException);

        if (!CryptUtils.verify(credentials.password(), account.getPassword())) {
            tryBlockAccount(account);
            throw ExceptionFactory.createInvalidCredentialsException();
        }

        if (!account.getState().equals(AccountState.ACTIVE)) {
            throw ExceptionFactory.createCantAuthenticateToNotActiveAccount();
        }

        String token = TokenUtils.generateToken(account.getLogin(), account.getRole().toString());

        return Json.createObjectBuilder()
                .add("token", token)
                .build();

    }

    private void tryBlockAccount(Account account) {
        AccountMetaData metaData = account.getMetaData();
        metaData.setNegativeAuthAttempts(metaData.getNegativeAuthAttempts() + 1);
        // TODO change 3 to property
        if (metaData.getNegativeAuthAttempts() == 3) {
            account.setState(AccountState.BLOCKED);
        }
        accountRepository.save(account);
    }
}
