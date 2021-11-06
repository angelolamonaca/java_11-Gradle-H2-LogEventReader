package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.concurrent.ThreadLogArchiver;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 19/10/2021
 */
@Slf4j
@AllArgsConstructor
public class LogFileServiceImpl implements LogFileService {
    @NonNull
    ExecutorService logFileExecutorService;

    @Override
    public void storeLogs(String logFilePath) {
        log.debug("Scanning file from {}", logFilePath);
        scanFile(logFilePath);
    }

    @Override
    public void scanFile(String logFilePath) {
        FileInputStream inputStream = null;
        Scanner scanner = null;
        try {
            File file = new File(logFilePath);
            if (!file.exists()) {
                throw new Exception("File is not valid");
            }
            inputStream = new FileInputStream(logFilePath);
            scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
            StringBuilder eventLogAsString = new StringBuilder();
            log.debug("Reading logs from file {}", file);
            while (scanner.hasNextLine()) {
                String eventLogLine = scanner.nextLine();
                eventLogAsString.append(eventLogLine);
                if (eventLogAsString.toString().contains("}")) {
                    runLogArchiverThread(eventLogAsString.toString());
                    eventLogAsString.setLength(0);
                }
            }
            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private void runLogArchiverThread(String eventLogAsString) {
        ThreadLogArchiver threadLogArchiver = new ThreadLogArchiver(eventLogAsString, "Thread Log Archiver");
        logFileExecutorService.submit(threadLogArchiver);
        log.debug("Submitted new {} for {}", threadLogArchiver, eventLogAsString);
    }
}
