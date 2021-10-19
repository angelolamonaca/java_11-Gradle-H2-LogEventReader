package com.angelolamonaca.logreader.data;

import com.angelolamonaca.logreader.entity.Event;

import java.util.List;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 19/10/2021
 */
public interface EventDAO {
    public void addEvent(Event e);

    public void updateEvent(Event e);

    public List<Event> listEvents();
}
