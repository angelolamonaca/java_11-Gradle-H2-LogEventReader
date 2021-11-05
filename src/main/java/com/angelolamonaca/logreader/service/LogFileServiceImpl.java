package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.concurrent.RunnableLogArchiver;
import lombok.AllArgsConstructor;
import lombok.NonNull;

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
@AllArgsConstructor
public class LogFileServiceImpl implements LogFileService {
    @NonNull
    ExecutorService logFileExecutorService;

    @Override
    public void storeLogs(String logFilePath) {
        scanFile(logFilePath);
    }

    @Override
    public void scanFile(String logFilePath) {
        FileInputStream inputStream = null;
        Scanner scanner = null;
        try {
            File file = new File(logFilePath);
            if (!file.exists()) {
                throw new Exception("File not found");
            }
            inputStream = new FileInputStream(logFilePath);
            scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
            StringBuilder eventLogAsString = new StringBuilder();
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
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private void runLogArchiverThread(String eventLogAsString) {
        RunnableLogArchiver runnableLogArchiver = new RunnableLogArchiver(eventLogAsString);
        logFileExecutorService.submit(runnableLogArchiver);
    }
}
