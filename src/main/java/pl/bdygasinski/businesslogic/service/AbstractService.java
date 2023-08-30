package pl.bdygasinski.businesslogic.service;

import jakarta.annotation.Resource;
import jakarta.ejb.*;
import lombok.extern.java.Log;

import java.rmi.RemoteException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

@Log
public abstract class AbstractService implements SessionSynchronization {

    @Resource
    private SessionContext sctx;

    private boolean lastTransactionRollback;
    private Long transactionId;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    private boolean isLastTransactionRollback() {
        return this.lastTransactionRollback;
    }

    @Override
    public void afterBegin() throws EJBException, RemoteException {
        transactionId = System.currentTimeMillis() + ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);

        log.log(Level.INFO, "Transaction with id={0} started in {1}, identity: {2}",
                new Object[] {
                        transactionId, getClass().getName(), sctx.getCallerPrincipal().getName()
                });
    }

    @Override
    public void beforeCompletion() throws EJBException, RemoteException {
        log.log(Level.INFO, "Transaction with id={0} before commit in {1}, identity: {2}",
                new Object[] {
                        transactionId, getClass().getName(), sctx.getCallerPrincipal().getName()
                });
    }

    @Override
    public void afterCompletion(boolean committed) throws EJBException, RemoteException {
        lastTransactionRollback = !committed;
        String message = "Transaction with id={0} ends in {1}, with status {2}, identity {3}";

        if (lastTransactionRollback) {
            log.log(Level.WARNING, message, new Object[] {
                            transactionId,
                            getClass().getName(),
                            "ROLLBACK",
                            sctx.getCallerPrincipal().getName()
                    });
        } else {
            log.log(Level.INFO, message, new Object[] {
                            transactionId,
                            getClass().getName(),
                            "COMMIT",
                            sctx.getCallerPrincipal().getName()
                    });
        }
    }
}
