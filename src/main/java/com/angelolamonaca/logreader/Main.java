package com.angelolamonaca.logreader;

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
        // String logFilePath = askForLogFilePath();
        String logFilePath = "src/main/resources/log";
        log.info("Log file path: {}", logFilePath);
        new LogReader().execute(logFilePath);
    }

    static String askForLogFilePath() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter log file path");
        String input = sc.nextLine();
        sc.close();
        return input;
    }
}
