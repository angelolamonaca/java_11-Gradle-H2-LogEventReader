package com.angelolamonaca.logreader.concurrent;

import com.angelolamonaca.logreader.entity.EventLog;
import com.angelolamonaca.logreader.service.EventLogServiceImpl;
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
public class ThreadLogArchiver extends Thread {
    EventLogServiceImpl eventLogService = new EventLogServiceImpl();

    @NonNull
    private EventLog eventLog;

    @NonNull
    private String threadName;

    @Override
    public void run() {
        log.debug("Starting {}", this);
        eventLogService.registerEventLog(this.eventLog);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", " +
                super.toString() + ", " +
                "id: " + super.getId() + ", " +
                "thread name: " + threadName;
    }
}
