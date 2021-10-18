package com.angelolamonaca.logreader.controller;

import com.angelolamonaca.logreader.model.Event;
import com.angelolamonaca.logreader.model.EventDao;
import lombok.NoArgsConstructor;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@NoArgsConstructor
public class EventControllerImpl implements EventController {
    private final EventDao eventDao = new EventDao();

    @Override
    public int registerEvent(Event event) {
        return eventDao.insertEvent(event);
    }
}
