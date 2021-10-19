package com.angelolamonaca.logreader;

import com.angelolamonaca.logreader.entity.EventLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@Slf4j
public class Main {
    public static void main(String... args) {
//        String logFilePath = input();
        String logFilePath = "src/main/resources/external_log";
        HashSet<EventLog> eventLogs = fileToEventLogs(logFilePath);
    }

    static String input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter log file path");
        return sc.nextLine();
    }

    static HashSet<EventLog> fileToEventLogs(String logFilePath) {
        HashSet<EventLog> eventLogs = null;
        try {
            File file = new File(logFilePath);
            if (file.exists()) {
                InputStream inputStream = new FileInputStream(logFilePath);
                String logFileAsString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                String[] eventLogsAsStrings = logFileAsString.split("(?=\\{)");
                eventLogs = new HashSet<>();
                for (String eventLogAsString : eventLogsAsStrings) {
                    eventLogs.add(new ObjectMapper().readValue(eventLogAsString, EventLog.class));
                }
                return eventLogs;
            } else {
                log.error("Log File in {} not found", logFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventLogs;
    }
}
