package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@NoArgsConstructor
public class EventServiceImpl implements EventService {

    @Override
    public void registerEvent(Event event) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Event e = new Event("asdasd", 350, "test", "test", true);
        session.save(e);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
