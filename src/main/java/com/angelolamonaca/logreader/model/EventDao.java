package com.angelolamonaca.logreader.model;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.angelolamonaca.logreader.Main.ds;
import static com.angelolamonaca.logreader.utils.ExceptionLogger.logException;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@Slf4j
@NoArgsConstructor
public class EventDao {
    public int insertEvent(Event event) {
        String INSERT_EVENT_SQL = "INSERT INTO event (id, duration, type, host, alert) VALUES (?, ?, ?, ?, ?);";
        int result = 0;
        try {
            Connection conn = ds.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_EVENT_SQL);
            preparedStatement.setLong(1, event.getId());
            preparedStatement.setLong(1, event.getDuration());
            preparedStatement.setString(1, event.getType());
            preparedStatement.setString(1, event.getHost());
            preparedStatement.setBoolean(1, event.getAlert());
            log.info("SQL PS CREATED: {}", preparedStatement.toString());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            logException(ex);
        }
        return result;
    }
}
