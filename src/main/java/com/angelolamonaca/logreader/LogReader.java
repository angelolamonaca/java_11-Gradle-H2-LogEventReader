package com.angelolamonaca.logreader;

import com.angelolamonaca.logreader.entity.Event;
import com.angelolamonaca.logreader.service.EventLogServiceImpl;
import com.angelolamonaca.logreader.service.EventServiceImpl;
import com.angelolamonaca.logreader.service.LogFileServiceImpl;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 05/11/2021
 */
@Slf4j
public class LogReader {
    int hibernatePoolSize = Integer.parseInt(HibernateUtil.getProperties().getProperty("hibernate.connection.pool_size"));
    ExecutorService logFileExecutor = Executors.newFixedThreadPool(hibernatePoolSize);
    ExecutorService eventExecutor = Executors.newFixedThreadPool(hibernatePoolSize);

    void execute(String logFilePath) {
        log.debug("Executing LogReader {}", this);
        storeLogsToDatabase(logFilePath);
        convertEventLogsToEvents();
    }

    void storeLogsToDatabase(String logFilePath) {
        try {
            LogFileServiceImpl logFileService = new LogFileServiceImpl(logFileExecutor);
            logFileService.storeLogs(logFilePath);
            logFileExecutor.shutdown();
            if (!logFileExecutor.awaitTermination(1, TimeUnit.HOURS)) {
                logFileExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    void convertEventLogsToEvents() {
        EventServiceImpl eventService = new EventServiceImpl(eventExecutor);
        EventLogServiceImpl eventLogService = new EventLogServiceImpl(eventExecutor);

        try {
            while (true) {
                Future<?> eventRetrieverFuture = eventService.runThreadEventRetriever();
                Event event = (Event) eventRetrieverFuture.get();
                if (event == null) {
                    log.debug("Exiting...");
                    break;
                }
                Future<?> eventRegistererFuture = eventService.runThreadEventRegisterer(event);
                Future<?> eventLogRemoverFuture = eventLogService.runThreadEventLogRemover(event.getId());
                Integer eventLogsRemoved = (Integer) eventLogRemoverFuture.get();
                log.debug("Removed {} EventLogs from DB", eventLogsRemoved);
            }
            eventExecutor.shutdown();
            if (!eventExecutor.awaitTermination(1, TimeUnit.HOURS)) {
                eventExecutor.shutdownNow();
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
        }
    }
}
