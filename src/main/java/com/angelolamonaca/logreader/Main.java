package com.angelolamonaca.logreader;

import com.angelolamonaca.logreader.entity.EventMap;
import com.angelolamonaca.logreader.service.EventServiceImpl;
import com.angelolamonaca.logreader.service.LogFileServiceImpl;
import com.angelolamonaca.logreader.utils.HibernateUtil;
import lombok.extern.slf4j.Slf4j;

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
        LogFileServiceImpl logFileService = new LogFileServiceImpl();
        String[] logsAsStringArray = logFileService.extractLogsFromFile(logFilePath);
        EventMap eventMap = EventMap.of(logsAsStringArray);
        EventServiceImpl eventService = new EventServiceImpl();
        eventService.registerEvents(eventMap);
        HibernateUtil.shutdown();
    }

    static String input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter log file path");
        String input = sc.nextLine();
        sc.close();
        return input;
    }
}
