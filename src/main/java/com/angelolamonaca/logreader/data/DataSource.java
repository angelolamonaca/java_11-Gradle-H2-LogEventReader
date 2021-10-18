package com.angelolamonaca.logreader.data;

import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.angelolamonaca.logreader.utils.ExceptionLogger.logException;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@Slf4j
public class DataSource {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/logreader";
    private static final String USER = "sa";
    private static final String PASS = "sa";

    public static JdbcDataSource createDataSource() {
        log.info("Creating DataSource");
        JdbcDataSource ds = new JdbcDataSource();
        log.info("DataSource {} correctly created!", ds.getTraceObjectName());
        log.info("Setting DataSource URL {}", DB_URL);
        ds.setURL(DB_URL);
        log.info("Setting DataSource User {}", USER);
        ds.setUser(USER);
        log.info("Setting DataSource Password {}", PASS);
        ds.setPassword(PASS);

        Connection conn = null;
        Statement stmt = null;
        try {
            log.info("Registering JDBC driver");
            Class.forName(JDBC_DRIVER);
            log.info("Connecting to the database");
            conn = ds.getConnection();
            log.info("Check if EVENT table exists");
            stmt = conn.createStatement();
            ResultSet rs = conn.getMetaData().getTables(null, null, "EVENT", null);
            if (rs.first()) {
                log.info("EVENT table already exists, skipping creation");
            } else {
                log.info("EVENT table not found, creating EVENT table");
                String CREATE_EVENT_TABLE_SQL =
                        "CREATE TABLE EVENT " +
                                "(" +
                                "id LONG not null primary key, " +
                                "duration INTEGER not null, " +
                                "type VARCHAR(255), " +
                                "host VARCHAR(255), " +
                                "alert BOOLEAN" +
                                ")";
                stmt.executeUpdate(CREATE_EVENT_TABLE_SQL);
                log.info("EVENT table successful created");
            }
        } catch (SQLException ex) {
            logException(ex);
        } catch (ClassNotFoundException ex) {
            logException(ex);
        }
        return ds;
    }
}
