package com.solvd.onlinemarkettc.persistence.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private static final Logger log = LogManager.getLogger(CustomRunnable.class);
    private final String ID;
    private Boolean isActive = true;
    private java.sql.Connection sqlConnection;

    public Connection(String id) {
        this.ID = id;
        try {
            String url = DatabaseConfig.getUrl();
            String username = DatabaseConfig.getUsername();
            String password = DatabaseConfig.getPassword();

            this.sqlConnection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            this.isActive = false;
            throw new RuntimeException("failed to create database connection", e);
        }
        log.info("connection creatted id= {}", id);
    }

    public String getID() {
        return ID;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public java.sql.Connection getSqlConnection() {
        return sqlConnection;
    }

    public void close() {
        try {
            sqlConnection.close();
            log.info("connection {} closed", ID);

        } catch (SQLException e) {
            log.error("failed to close connection");
            throw new RuntimeException(e);
        } finally {
            this.isActive = false;

        }

    }
}
