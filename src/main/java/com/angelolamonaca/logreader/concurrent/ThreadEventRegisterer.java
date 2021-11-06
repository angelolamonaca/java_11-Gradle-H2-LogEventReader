package com.angelolamonaca.logreader.concurrent;

import com.angelolamonaca.logreader.data.EventDAOImpl;
import com.angelolamonaca.logreader.data.EventLogDAOImpl;
import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.service.EventLogServiceImpl;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 05/11/2021
 */
@Slf4j
@NoArgsConstructor
@RequiredArgsConstructor
public class ThreadEventRegisterer extends Thread {
    EventDAOImpl eventDAO = new EventDAOImpl(HibernateUtil.getSessionFactory());

    @NonNull
    private Event event;

    @NonNull
    private String threadName;

    @Override
    public void run() {
        eventDAO.addEvent(event);
        log.debug("Starting {}", this);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", " +
                super.toString() + ", " +
                "id: " + super.getId() + ", " +
                "thread name: " + threadName;
    }
}
