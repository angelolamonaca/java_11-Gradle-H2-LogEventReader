package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.entity.EventLog;

import java.util.List;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 06/11/2021
 */
public interface EventLogService {
    EventLog retrieveFirstEventLog();
    List<EventLog> retrieveEventLogs(String eventLogId);
    void registerEventLog(EventLog eventLog);
}