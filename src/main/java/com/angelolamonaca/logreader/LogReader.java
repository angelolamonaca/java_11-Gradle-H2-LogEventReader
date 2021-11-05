package com.angelolamonaca.logreader;

import com.angelolamonaca.logreader.service.LogFileServiceImpl;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.extern.slf4j.Slf4j;

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
        log.debug("Storing logs to database");
        LogFileServiceImpl logFileService = new LogFileServiceImpl(logFileExecutorService);
        logFileService.storeLogs(logFilePath);
        logFileExecutorService.shutdown();
        try {
            if (!logFileExecutorService.awaitTermination(1, TimeUnit.HOURS)) {
                logFileExecutorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
