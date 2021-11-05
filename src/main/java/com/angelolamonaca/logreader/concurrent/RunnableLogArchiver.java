package com.angelolamonaca.logreader.concurrent;

import com.angelolamonaca.logreader.data.EventLogDAOImpl;
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
public class RunnableLogArchiver implements Runnable {

    EventLogDAOImpl eventLogDAO = new EventLogDAOImpl(HibernateUtil.getSessionFactory());

    @NonNull
    private String eventLogAsString;

    @Override
    public void run() {
        log.debug("Starting new thread {}", this);
        eventLogDAO.addEventLog(this.eventLogAsString);
    }
}
