package com.angelolamonaca.logreader;

import org.apache.commons.io.IOUtils;
import org.h2.jdbcx.JdbcDataSource;
import org.json.JSONArray;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

import static com.angelolamonaca.logreader.data.DataSource.createDataSource;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
public class Main {
    public static JdbcDataSource ds;

    public static void main(String... args) {
        String logFilePath = input();
        ds = createDataSource();
        JSONArray jsonArray = fileToJsonArray(logFilePath);
        System.out.println(jsonArray.getString(0));
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
