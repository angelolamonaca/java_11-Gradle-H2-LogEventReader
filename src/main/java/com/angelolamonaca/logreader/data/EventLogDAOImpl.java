package com.angelolamonaca.logreader.data;

import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.entity.EventLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import java.util.List;

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
    public EventLog getEventLog() {
        Session session = sessionFactory.openSession();
        List<EventLog> eventLogs = null;
        try {
            session.beginTransaction();
            log.debug("Attempting to load EventLog with id 0 from DB");
            TypedQuery<EventLog> query = session.createNativeQuery("select * from EventLog e", EventLog.class);
            query.setFirstResult(0);
            query.setMaxResults(1);
            eventLogs = query.getResultList();
            session.getTransaction().commit();
            log.debug("EventLog successful loaded from DB {}", eventLogs.get(0));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        session.close();
        assert eventLogs != null;
        return eventLogs.get(0);
    }

    @Override
    public List<Event> getEvents() {
        Session session = sessionFactory.openSession();
        List<Event> eventLogs = null;
        List<Event> events = null;
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
                            "where e.STATE=0 and el.STATE=1;", Event.class);
            eventLogs = query.getResultList();
//            events = eventLogs.stream().map(eventLog -> new Event(eventLog.getEventLogId())).collect(Collectors.toList());
            session.getTransaction().commit();
            log.debug("EventLogs successful loaded from DB {}", eventLogs);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        session.close();
        return eventLogs;
    }

    @Override
    public List<EventLog> getEventLogs(String id) {
        Session session = sessionFactory.openSession();
        List<EventLog> eventLogs = null;
        try {
            session.beginTransaction();
            log.debug("Attempting to load EventLog from DB");
            TypedQuery<EventLog> query = session.createNativeQuery("select * from EventLog e where e.eventLogId= :eventLogId", EventLog.class);
            query.setParameter("eventLogId", id);
            eventLogs = query.getResultList();
            session.getTransaction().commit();
            log.debug("EventLogs successful loaded from DB {}", eventLogs);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        session.close();
        return eventLogs;
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
}
