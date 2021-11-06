package com.angelolamonaca.logreader.data;

import com.angelolamonaca.logreader.entity.EventLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 05/11/2021
 */
@Slf4j
@Data
@AllArgsConstructor
public class EventLogDAOImpl implements EventLogDAO {

    private final SessionFactory sessionFactory;

    @Override
    public void addEventLog(String eventLogAsString) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            log.debug("Mapping EventLog object from string log {}", eventLogAsString);
            EventLog eventLog = new ObjectMapper().readValue(eventLogAsString, EventLog.class);
            log.debug("Attempting to insert EventLog {} into DB", eventLog);
            session.persist(eventLog);
            session.getTransaction().commit();
            log.debug("EventLog successful saved in DB {}", eventLog);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        session.close();
    }
}
