package com.angelolamonaca.logreader.controller;

import com.angelolamonaca.logreader.model.Event;
import com.angelolamonaca.logreader.model.EventDao;
import com.angelolamonaca.logreader.view.EventView;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@RequiredArgsConstructor
public class EventControllerImpl implements EventController {
    @NonNull
    private Event model;
    @NonNull
    private EventView view;
    private final EventDao eventDao = new EventDao();

    @Override
    public void setEventId(Long id) {
        model.setId(id);
    }

    @Override
    public Long getEventId() {
        return model.getId();
    }

    @Override
    public void setEventDuration(Integer duration) {
        model.setDuration(duration);
    }

    @Override
    public Integer getEventDuration() {
        return model.getDuration();
    }

    @Override
    public void setEventType(String type) {
        model.setType(type);
    }

    @Override
    public String getEventType() {
        return model.getType();
    }

    @Override
    public void setEventHost(String host) {
        model.setHost(host);
    }

    @Override
    public String getEventHost() {
        return model.getHost();
    }

    @Override
    public void setEventAlert(Boolean alert) {
        model.setAlert(alert);
    }

    @Override
    public Boolean getEventAlert() {
        return model.getAlert();
    }

    @Override
    public int registerEvent(Event event) {
        return eventDao.insertEvent(event);
    }

    @Override
    public void updateView() {
        view.printEventDetails(model);
    }
}
