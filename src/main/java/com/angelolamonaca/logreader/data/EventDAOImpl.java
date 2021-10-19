package com.angelolamonaca.logreader.data;


import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 19/10/2021
 */
@Slf4j
@Data
public class EventDAOImpl implements EventDAO {
    private final Session session;

    public EventDAOImpl(Session session) {
        this.session = session;
        session.beginTransaction();
    }

    @Override
    public void addEvent(Event e) {
        session.persist(e);
        log.info("Event {} saved successfully", e.getId());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateEvent(Event e) {
        session.persist(e);
        log.info("Event {} updated successfully", e.getId());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Event> listEvents() {
        return session.createQuery("SELECT a FROM Event a", Event.class).getResultList();
    }
}
