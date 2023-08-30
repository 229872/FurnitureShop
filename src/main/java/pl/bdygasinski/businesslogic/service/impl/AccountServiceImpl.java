package pl.bdygasinski.businesslogic.service.impl;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import pl.bdygasinski.businesslogic.model.AccountDataForUpdate;
import pl.bdygasinski.businesslogic.service.AbstractService;
import pl.bdygasinski.businesslogic.service.api.AccountService;
import pl.bdygasinski.dataaccess.entity.Account;
import pl.bdygasinski.dataaccess.entity.Address;
import pl.bdygasinski.dataaccess.entity.PersonalData;
import pl.bdygasinski.dataaccess.entity.enums.AccountState;
import pl.bdygasinski.dataaccess.repository.api.AccountRepository;
import pl.bdygasinski.exception.ExceptionFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public final class AccountServiceImpl extends AbstractService implements AccountService {

    @Inject
    private AccountRepository accountRepository;


    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Long id, AccountDataForUpdate updateData) {
        Account account = accountRepository.findById(id)
                .orElseThrow(ExceptionFactory::createAccountNotFoundException);

        Account updatedAccount = updateAccount(account, updateData);
        return accountRepository.save(updatedAccount);
    }

    private Account updateAccount(Account account, AccountDataForUpdate updateData) {
        PersonalData personalData = account.getPersonalData();
        Address address = personalData.getAddress();

        Optional.ofNullable(updateData.locale()).ifPresent(account::setLocale);
        Optional.ofNullable(updateData.firstName()).ifPresent(personalData::setFirstName);
        Optional.ofNullable(updateData.lastName()).ifPresent(personalData::setLastName);
        Optional.ofNullable(updateData.dateOfBirth()).ifPresent(personalData::setDateOfBirth);
        Optional.ofNullable(updateData.country()).ifPresent(address::setCountry);
        Optional.ofNullable(updateData.city()).ifPresent(address::setCity);
        Optional.ofNullable(updateData.street()).ifPresent(address::setStreet);
        Optional.ofNullable(updateData.houseNumber()).ifPresent(address::setHouseNumber);
        Optional.ofNullable(updateData.postalCode()).ifPresent(address::setPostalCode);

        return account;
    }

    @Override
    public Account block(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(ExceptionFactory::createAccountNotFoundException);

        if (!account.getState().equals(AccountState.ACTIVE)) {
            throw ExceptionFactory.createCantBlockNotActiveAccountException();
        }

        account.setState(AccountState.BLOCKED);
        return accountRepository.save(account);
    }

    @Override
    public Account unblock(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(ExceptionFactory::createCantUnblockNotBlockedAccountException);

        if (!account.getState().equals(AccountState.BLOCKED)) {
            throw ExceptionFactory.createCantUnblockNotBlockedAccountException();
        }

        account.setState(AccountState.ACTIVE);
        return accountRepository.save(account);
    }

    @Override
    public Account archive(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(ExceptionFactory::createCantUnblockNotBlockedAccountException);

        account.setState(AccountState.ARCHIVAL);
        return accountRepository.save(account);
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(ExceptionFactory::createAccountNotFoundException);
    }

    @Override
    public Account findByLogin(String login) {
        return accountRepository.findByLogin(login)
                .orElseThrow(ExceptionFactory::createAccountNotFoundException);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(ExceptionFactory::createAccountNotFoundException);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

}
