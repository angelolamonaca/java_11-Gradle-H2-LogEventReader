package com.angelolamonaca.logreader.controller;

import com.angelolamonaca.logreader.model.Event;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
public interface EventController {
    void setEventId(Long id);
    Long getEventId();

    void setEventDuration(Integer duration);
    Integer getEventDuration();

    void setEventType(String type);
    String getEventType();

    void setEventHost(String host);
    String getEventHost();

    void setEventAlert(Boolean alert);
    Boolean getEventAlert();

    int registerEvent(Event event);

    void updateView();
}
