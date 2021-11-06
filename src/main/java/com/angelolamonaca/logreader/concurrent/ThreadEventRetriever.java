package com.angelolamonaca.logreader.concurrent;

import com.angelolamonaca.logreader.data.EventLogDAOImpl;
import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 05/11/2021
 */
@Slf4j
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class ThreadEventRetriever implements Callable<Event> {
    EventLogDAOImpl eventLogDAO = new EventLogDAOImpl(HibernateUtil.getSessionFactory());

    @NonNull
    private String threadName;

    @Override
    public Event call() {
        log.debug("Starting {}", this);
        return eventLogDAO.getEvent();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", " +
                "thread name: " + threadName;
    }
}
