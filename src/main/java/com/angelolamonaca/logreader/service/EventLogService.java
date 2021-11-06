package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.entity.EventLog;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 06/11/2021
 */
public interface EventLogService {
    void registerEventLog(EventLog eventLog);
    Future<?> runThreadEventLogRemover(String eventLogId);
}
