package pl.bdygasinski.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Connection;

@ApplicationScoped
@DataSourceDefinitions({
        @DataSourceDefinition(
                name = "java:app/init",
                className = "org.postgresql.ds.PGSimpleDataSource",
                serverName = "localhost",
                portNumber = 5555,
                databaseName = "furniture_shop",
                user = "shop_creator",
                //You have to set environment variable in your OS or in application server.
                password = "${DB_PASSWORD}",
                isolationLevel = Connection.TRANSACTION_READ_COMMITTED,
                initialPoolSize = 4,
                minPoolSize = 2,
                maxPoolSize = 6,
                description = "Data source for creating tables"
        )
})
public class DataSourceConfig {

    @PersistenceContext(unitName = "init")
    private EntityManager entityManager;
}
