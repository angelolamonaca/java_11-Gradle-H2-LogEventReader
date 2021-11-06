package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.entity.EventLog;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 06/11/2021
 */
public interface EventLogService {
    void registerEventLog(EventLog eventLog);
}
