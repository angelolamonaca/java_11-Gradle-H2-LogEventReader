package com.angelolamonaca.logreader.service;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 19/10/2021
 */
public class LogFileServiceImpl implements LogFileService {
    @Override
    public String[] extractLogsFromFile(String logFilePath) {
        String[] logsAsStringArray = new String[0];
        try {
            File file = new File(logFilePath);
            if (file.exists()) {
                InputStream inputStream = new FileInputStream(logFilePath);
                String logFileAsString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                logsAsStringArray = logFileAsString.split("(?=\\{)");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return logsAsStringArray;
    }
}
