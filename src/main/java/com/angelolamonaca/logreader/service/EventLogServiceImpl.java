package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.concurrent.ThreadEventLogRemover;
import com.angelolamonaca.logreader.data.EventLogDAOImpl;
import com.angelolamonaca.logreader.entity.EventLog;
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
 * @since 06/11/2021
 */
@Slf4j
@NoArgsConstructor
@RequiredArgsConstructor
public class EventLogServiceImpl implements EventLogService {
    @NonNull
    ExecutorService eventExecutor;
    EventLogDAOImpl eventLogDAO = new EventLogDAOImpl(HibernateUtil.getSessionFactory());


    @Override
    public void registerEventLog(EventLog eventLog) {
        eventLogDAO.addEventLog(eventLog);
    }

    @Override
    public Future<?> runThreadEventLogRemover(String eventLogId) {
        ThreadEventLogRemover threadEventLogRemover = new ThreadEventLogRemover(eventLogId, "Thread Event Remover");
        log.debug("Submitting new {} for {}", threadEventLogRemover, eventLogId);
        return eventExecutor.submit(threadEventLogRemover);
    }
}
