package iti.jets.configuration;

import jakarta.persistence.EntityManagerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.Persistence;
import lombok.Getter;

import javax.sql.DataSource;

public class HikariCPConfig {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String JDBC_USERNAME = "username";
    private static final String JDBC_PASSWORD = "password";

    private static final String PERSISTENCE_UNIT_NAME = "HRcompany";


    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    private static final int MAX_POOL_SIZE = 10;
    private static final int MIN_IDLE = 5;
    private static final long CONNECTION_TIMEOUT = 30000; // 30 seconds

    @Getter
    private static DataSource dataSource;
    @Getter
    private static EntityManagerFactory entityManagerFactory;


    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(JDBC_USERNAME);
        config.setPassword(JDBC_PASSWORD);
        config.setDriverClassName(DRIVER_CLASS_NAME);
        config.setMaximumPoolSize(MAX_POOL_SIZE);
        config.setMinimumIdle(MIN_IDLE);
        config.setConnectionTimeout(CONNECTION_TIMEOUT);

        dataSource = new HikariDataSource(config);
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    }

}
