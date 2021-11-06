package com.angelolamonaca.logreader.data;

import com.angelolamonaca.logreader.entity.EventLog;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 05/11/2021
 */
public interface EventLogDAO {
    void addEventLog(EventLog eventLog);
}
