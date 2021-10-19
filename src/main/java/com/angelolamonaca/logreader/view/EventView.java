package com.angelolamonaca.logreader.view;

import com.angelolamonaca.logreader.entity.Event;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@Slf4j
public class EventView {
    public void printEventDetails(Event event) {
        System.out.println("Event:");
        System.out.println("Id:" + event.getId());
        System.out.println("Duration:" + event.getDuration());
        if (event.getType() != null) {
            System.out.println("Type:" + event.getType());
        } else {
            System.out.println("Type: NA");
        }
        if (event.getHost() != null) {
            System.out.println("Host:" + event.getHost());
        } else {
            System.out.println("Host: NA");
        }
        if (event.getAlert() != null) {
            System.out.println("Alert:" + event.getAlert());
        } else {
            System.out.println("Alert: NA");
        }
    }
}
