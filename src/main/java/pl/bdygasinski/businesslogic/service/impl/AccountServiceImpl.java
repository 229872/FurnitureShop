package pl.bdygasinski.businesslogic.service.impl;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import pl.bdygasinski.businesslogic.model.AccountDataForUpdate;
import pl.bdygasinski.businesslogic.service.api.AccountService;
import pl.bdygasinski.dataaccess.entity.Account;
import pl.bdygasinski.dataaccess.entity.Address;
import pl.bdygasinski.dataaccess.entity.PersonalData;
import pl.bdygasinski.dataaccess.repository.api.AccountRepository;
import pl.bdygasinski.exception.ExceptionFactory;

import java.util.List;
import java.util.Optional;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public final class AccountServiceImpl implements AccountService {

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
        return null;
    }

    @Override
    public Account unblock(Long id) {
        return null;
    }

    @Override
    public Account archive(Long id) {
        return null;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public Account findByLogin(String login) {
        return null;
    }

    @Override
    public Account findByEmail(String email) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

}
