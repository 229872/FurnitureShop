package pl.bdygasinski.dataaccess.repository.impl;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.bdygasinski.dataaccess.entity.Account;
import pl.bdygasinski.dataaccess.repository.AbstractRepository;
import pl.bdygasinski.dataaccess.repository.api.AccountRepository;
import pl.bdygasinski.exception.ExceptionFactory;

import java.util.Optional;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AccountRepositoryImpl extends AbstractRepository<Account> implements AccountRepository {

    @PersistenceContext(unitName = "account")
    private EntityManager entityManager;

    public AccountRepositoryImpl() {
        super(Account.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Account save(Account entity) {
        try {
            Account account = super.save(entity);
            entityManager.flush();
            return account;
        } catch (PersistenceException e) {
            String message = e.getMessage();

            if (message.contains("account_login_key")) {
                throw ExceptionFactory.createLoginConflictException();
            } else if (message.contains("account_email_key")) {
                throw ExceptionFactory.createEmailConflictException();
            } else {
                throw ExceptionFactory.createUnknownException();
            }
        }
    }

    @Override
    public void delete(Account entity) {
        try {
            super.delete(entity);
            entityManager.flush();
        } catch (PersistenceException e) {
            throw ExceptionFactory.createUnknownException();
        }
    }

    @Override
    public Optional<Account> findByLogin(String login) {
        return getEntityManager()
                .createNamedQuery(Account.FIND_BY_LOGIN, getClazz())
                .setParameter(0, login)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return getEntityManager()
                .createNamedQuery(Account.FIND_BY_EMAIL, getClazz())
                .setParameter(0, email)
                .getResultStream()
                .findFirst();
    }
}
