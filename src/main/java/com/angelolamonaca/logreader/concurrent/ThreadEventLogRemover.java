package com.angelolamonaca.logreader.concurrent;

import com.angelolamonaca.logreader.data.EventLogDAOImpl;
import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.entity.EventLog;
import com.angelolamonaca.logreader.service.EventLogServiceImpl;
import com.angelolamonaca.logreader.utils.HibernateUtil;
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
@NoArgsConstructor
@RequiredArgsConstructor
public class ThreadEventLogRemover implements Callable<Integer> {
    EventLogDAOImpl eventLogDAO = new EventLogDAOImpl(HibernateUtil.getSessionFactory());

    @NonNull
    private String eventLogId;

    @NonNull
    private String threadName;

    @Override
    public Integer call() {
        log.debug("Starting {}", this);
        return eventLogDAO.deleteEventLogById(eventLogId);
    }
}
