package com.angelolamonaca.logreader.data;


import com.angelolamonaca.logreader.entity.Event;
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
@RequiredArgsConstructor
public class EventDAOImpl implements EventDAO {
    private final SessionFactory sessionFactory;

    @Override
    public void addEvent(Event e) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(e);
        log.info("Event {} saved successfully" + e.getId());
    }

    @Override
    public void updateEvent(Event e) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(e);
        log.info("Event {} updated successfully" + e.getId());
    }

    @Override
    public List<Event> listEvents() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("SELECT a FROM Event a", Event.class).getResultList();
    }
}
