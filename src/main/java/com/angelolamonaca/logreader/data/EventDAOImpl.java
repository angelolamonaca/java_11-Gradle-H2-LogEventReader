package com.angelolamonaca.logreader.data;


import com.angelolamonaca.logreader.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 19/10/2021
 */
@Slf4j
@Data
@AllArgsConstructor
public class EventDAOImpl implements EventDAO {
    private final SessionFactory sessionFactory;

    @Override
    public void addEvent(Event event) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            log.debug("Attempting to insert Event {} into DB", event);
            session.persist(event);
            session.getTransaction().commit();
            log.debug("Event successful saved in DB {}", event);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        session.close();
    }
}
