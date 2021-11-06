package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.data.EventDAOImpl;
import com.angelolamonaca.logreader.data.EventLogDAOImpl;
import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.entity.EventLog;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@NoArgsConstructor
public class EventServiceImpl implements EventService {
    EventDAOImpl eventDAO = new EventDAOImpl(HibernateUtil.getSessionFactory());
    EventLogDAOImpl eventLogDAO = new EventLogDAOImpl(HibernateUtil.getSessionFactory());

    @Override
    public void registerEvent(Event event) {
        eventDAO.addEvent(event);
    }

    @Override
    public Event retrieveEvent() {
        return eventLogDAO.getEvent();
    }
}
