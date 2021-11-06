package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.data.EventLogDAOImpl;
import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.entity.EventLog;
import com.angelolamonaca.logreader.utils.HibernateUtil;

import java.util.List;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 06/11/2021
 */
public class EventLogServiceImpl implements EventLogService {
    EventLogDAOImpl eventLogDAO = new EventLogDAOImpl(HibernateUtil.getSessionFactory());


    @Override
    public void registerEventLog(EventLog eventLog) {
        eventLogDAO.addEventLog(eventLog);
    }


    @Override
    public int removeEventLogById(String eventLogId) {
        return eventLogDAO.deleteEventLogById(eventLogId);
    }
}
