package com.angelolamonaca.logreader;

import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.entity.EventDetails;
import com.angelolamonaca.logreader.entity.EventLog;
import com.angelolamonaca.logreader.service.EventLogServiceImpl;
import com.angelolamonaca.logreader.service.EventServiceImpl;
import com.angelolamonaca.logreader.service.LogFileServiceImpl;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 05/11/2021
 */
@Slf4j
public class LogReader {
    int hibernatePoolSize = Integer.parseInt(HibernateUtil.getProperties().getProperty("hibernate.connection.pool_size"));
    ExecutorService logFileExecutorService = Executors.newFixedThreadPool(hibernatePoolSize);


    void storeLogsToDatabase(String logFilePath) {
        try {
            LogFileServiceImpl logFileService = new LogFileServiceImpl(logFileExecutorService);
            logFileService.storeLogs(logFilePath);
            logFileExecutorService.shutdown();
            if (!logFileExecutorService.awaitTermination(1, TimeUnit.HOURS)) {
                logFileExecutorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    void calculateAndStoreEvents() {
        EventServiceImpl eventService = new EventServiceImpl();
        List<Event> eventLogs = eventService.retrieveEvents();
        log.info(String.valueOf(eventLogs));
    }
}
