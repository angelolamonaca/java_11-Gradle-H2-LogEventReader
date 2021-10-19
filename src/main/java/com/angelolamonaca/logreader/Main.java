package com.angelolamonaca.logreader;

import com.angelolamonaca.logreader.service.EventServiceImpl;
import com.angelolamonaca.logreader.entity.Event;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
public class Main {
    public static void main(String... args) {
        String logFilePath = input();
        // src/main/resources/external_log.json
        JSONArray jsonArray = fileToJsonArray(logFilePath);
        System.out.println(jsonArray.getString(0));
        EventServiceImpl eventController = new EventServiceImpl();
        Event e = new Event("asdasd",350,"test","test", true);
    }

    static String input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter log file path");
        return sc.nextLine();
    }

    static JSONArray fileToJsonArray(String logFilePath) {
        try {
            File file = new File(logFilePath);
            if (file.exists()) {
                InputStream inputStream = new FileInputStream(logFilePath);
                String fileAsString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                String[] test = fileAsString.split("(?=\\{)");
                return new JSONArray(Arrays.asList(test));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }
}
