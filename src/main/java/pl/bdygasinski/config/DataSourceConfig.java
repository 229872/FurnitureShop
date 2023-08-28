package pl.bdygasinski.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Connection;

@ApplicationScoped
@DataSourceDefinitions({

        //You have to set environment variables in your OS or in application server to have access to database.
        @DataSourceDefinition(
                name = "java:app/init",
                className = "org.postgresql.ds.PGSimpleDataSource",
                serverName = "localhost",
                portNumber = 5555,
                databaseName = "furniture_shop",
                user = "shop_creator",
                password = "${SHOP_CREATOR_PASSWORD}",
                isolationLevel = Connection.TRANSACTION_READ_COMMITTED,
                initialPoolSize = 1,
                minPoolSize = 1,
                maxPoolSize = 1,
                description = "Data source for creating tables."
        ),
        @DataSourceDefinition(
                name = "java:app/account",
                className = "org.postgresql.ds.PGSimpleDataSource",
                serverName = "localhost",
                portNumber = 5555,
                databaseName = "furniture_shop",
                user = "shop_account_manager",
                password = "${SHOP_ACCOUNT_MANAGER_PASSWORD}",
                isolationLevel = Connection.TRANSACTION_READ_COMMITTED,
                initialPoolSize = 3,
                minPoolSize = 2,
                maxPoolSize = 5,
                description = "Data source for account functionality."

        )
})
public class DataSourceConfig {

    @PersistenceContext(unitName = "init")
    private EntityManager entityManager;
}
