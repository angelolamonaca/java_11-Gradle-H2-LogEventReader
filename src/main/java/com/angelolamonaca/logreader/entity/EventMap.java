package com.angelolamonaca.logreader.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 19/10/2021
 */
public class EventMap extends HashMap<String, EventDetails> {

    public static EventMap of(String[] logsAsStringArray) {
        EventMap eventMap = new EventMap();
        for (String stringLog : logsAsStringArray) {
            try {
                EventLog eventLog = new ObjectMapper().readValue(stringLog, EventLog.class);
                eventMap.insert(eventLog.getId(), eventLog.getTimestamp(), eventLog.getType(), eventLog.getHost());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return eventMap;
    }

    private void insert(String id, Timestamp timestamp, String type, String host) {
        EventDetails eventDetails = this.computeIfPresent(id, (key, val) -> new EventDetails(
                Math.abs(Duration.between(
                        Instant.ofEpochMilli(val.getDuration()),
                        Instant.ofEpochMilli(timestamp.getTime())
                ).toMillis()),
                type,
                host,
                Math.abs(Duration.between(
                        Instant.ofEpochMilli(val.getDuration()),
                        Instant.ofEpochMilli(timestamp.getTime())
                ).toMillis()) > 5
        ));
        if (eventDetails == null) {
            this.put(id, new EventDetails(timestamp.getTime(), type, host, false));
        }
    }
}
