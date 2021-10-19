package com.angelolamonaca.logreader.service;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 19/10/2021
 */
public interface LogFileService {
    String[] extractLogsFromFile(String logFilePath);
}
