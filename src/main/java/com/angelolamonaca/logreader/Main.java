package com.angelolamonaca.logreader;

import com.angelolamonaca.logreader.controller.EventControllerImpl;
import com.angelolamonaca.logreader.model.Event;
import com.angelolamonaca.logreader.view.EventView;
import org.h2.jdbcx.JdbcDataSource;

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
        //String logFilePath = input();
        String logFilePath = "/external_log.txt";
        ds = createDataSource();
        Event model = retrieveEventFromDatabase();
        EventView view = new EventView();
        EventControllerImpl controller = new EventControllerImpl(model, view);
        controller.updateView();

    }

    static String input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter log file path");
        return sc.nextLine();
    }

    private static Event retrieveEventFromDatabase() {
        return new Event(1L, 12345);
    }
}
