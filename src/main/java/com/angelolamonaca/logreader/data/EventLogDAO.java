package com.angelolamonaca.logreader.data;

import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.entity.EventLog;

import java.util.List;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 05/11/2021
 */
public interface EventLogDAO {
    Event getEvent();
    void addEventLog(EventLog eventLog);
    int deleteEventLogById(String eventLogId);
}
