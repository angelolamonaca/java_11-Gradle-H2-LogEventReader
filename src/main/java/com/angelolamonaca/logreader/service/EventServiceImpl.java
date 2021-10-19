package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.data.EventDAOImpl;
import com.angelolamonaca.logreader.entity.EventMap;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.NoArgsConstructor;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@NoArgsConstructor
public class EventServiceImpl implements EventService {
    EventDAOImpl eventDAO = new EventDAOImpl(HibernateUtil.getSessionFactory());

    @Override
    public void registerEvents(EventMap eventMap) {
        eventDAO.addEvents(eventMap);
    }
}
