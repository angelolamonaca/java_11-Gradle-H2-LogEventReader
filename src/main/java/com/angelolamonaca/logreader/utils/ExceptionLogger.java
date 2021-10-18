package com.angelolamonaca.logreader.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@Slf4j
public class ExceptionLogger {
    public static void logException(ClassNotFoundException ex) {
        ex.printStackTrace(System.err);
        log.error("Message: {}\nCause: {}",
                ex.getMessage(), ex.getCause());
    }

    public static void logException(SQLException ex) {
        ex.printStackTrace(System.err);
        log.error("SQLState: {}\nError Code: {}\nMessage: {}\nCause: {}",
                ex.getSQLState(), ex.getErrorCode(), ex.getMessage(), ex.getCause());
    }
}
