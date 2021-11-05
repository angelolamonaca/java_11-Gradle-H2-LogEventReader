package com.angelolamonaca.logreader.concurrent;

import com.angelolamonaca.logreader.data.EventLogDAOImpl;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 05/11/2021
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class RunnableLogArchiver implements Runnable {

    EventLogDAOImpl eventLogDAO = new EventLogDAOImpl(HibernateUtil.getSessionFactory());

    @NonNull
    private String eventLogAsString;

    @Override
    public void run() {
        eventLogDAO.addEventLog(this.eventLogAsString);
    }
}
