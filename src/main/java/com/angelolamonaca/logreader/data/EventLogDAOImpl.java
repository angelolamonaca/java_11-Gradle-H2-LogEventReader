package com.angelolamonaca.logreader.data;

import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.entity.EventLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;

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
    public Event getEvent() {
        Session session = sessionFactory.openSession();
        Event event = null;
        try {
            session.beginTransaction();
            log.debug("Attempting to load EventLog from DB");
            TypedQuery<Event> query = session.createNativeQuery(
                    "select e.EVENTLOGID as ID, " +
                            "e.HOST, " +
                            "e.TYPE, " +
                            "abs(extract(millisecond from (e.TIMESTAMP - el.TIMESTAMP))) as DURATION, " +
                            "abs(extract(millisecond from (e.TIMESTAMP - el.TIMESTAMP))) > 5  as ALERT " +
                            "from EVENTLOG e INNER JOIN EVENTLOG el ON e.EVENTLOGID=el.EVENTLOGID " +
                            "where e.STATE=0 and el.STATE=1", Event.class);
            query.setFirstResult(0);
            query.setMaxResults(1);
            if (query.getResultList().size()==0) {
                log.debug("No more event logs in table EventLog");
                return null;
            }
            event = query.getResultList().get(0);
            session.getTransaction().commit();
            log.debug("Event successful loaded from DB {}", event);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        session.close();
        return event;
    }

    @Override
    public void addEventLog(EventLog eventLog) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            log.debug("Attempting to insert EventLog {} into DB", eventLog);
            session.persist(eventLog);
            session.getTransaction().commit();
            log.debug("EventLog successful saved in DB {}", eventLog);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        session.close();
    }

    @Override
    public int deleteEventLogById(String eventLogId) {
        Session session = sessionFactory.openSession();
        int deleted = 0;
        try {
            session.beginTransaction();
            log.debug("Attempting to delete EventLog {} from DB", eventLogId);
            TypedQuery<EventLog> query = session.createNativeQuery(
                    "delete from EVENTLOG where EVENTLOGID=:eventLogId", EventLog.class);
            query.setParameter("eventLogId", eventLogId);
            deleted = query.executeUpdate();
            session.getTransaction().commit();
            log.debug("EventLog successful deleted from DB {}", eventLogId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        session.close();
        return deleted;
    }
}
