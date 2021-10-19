package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.data.EventDAOImpl;
import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.NoArgsConstructor;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@NoArgsConstructor
public class EventServiceImpl implements EventService {
    EventDAOImpl eventDAO = new EventDAOImpl(HibernateUtil.getSessionFactory().openSession());

    @Override
    public void registerEvent(Event e) {
        eventDAO.addEvent(e);
        HibernateUtil.shutdown();
    }
}
