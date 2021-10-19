package com.angelolamonaca.logreader.data;


import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.entity.EventMap;
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
    public void addEvents(EventMap eventMap) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        eventMap.forEach((eventId, eventDetails) -> session.persist(new Event(eventId, eventDetails)));
        session.getTransaction().commit();
        session.close();
    }
}
