package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.concurrent.ThreadEventRegisterer;
import com.angelolamonaca.logreader.concurrent.ThreadEventRetriever;
import com.angelolamonaca.logreader.data.EventLogDAOImpl;
import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@Slf4j
@NoArgsConstructor
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    @NonNull
    ExecutorService eventExecutor;
    EventLogDAOImpl eventLogDAO = new EventLogDAOImpl(HibernateUtil.getSessionFactory());

    @Override
    public Future<?> runThreadEventRegisterer(Event event) {
        ThreadEventRegisterer threadEventRegisterer = new ThreadEventRegisterer(event, "Thread Event Remover");
        log.debug("Submitting new {} for {}", threadEventRegisterer, event);
        return eventExecutor.submit(threadEventRegisterer);
    }

    @Override
    public Future<?> runThreadEventRetriever() {
        ThreadEventRetriever threadEventRetriever = new ThreadEventRetriever("Thread Event Remover");
        log.debug("Submitting new {}", threadEventRetriever);
        return eventExecutor.submit(threadEventRetriever);
    }

    @Override
    public Event retrieveEvent() {
        return eventLogDAO.getEvent();
    }
}
