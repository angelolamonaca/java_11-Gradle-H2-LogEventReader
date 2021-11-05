package com.angelolamonaca.logreader.data;

import com.angelolamonaca.logreader.entity.Event;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 19/10/2021
 */
public interface EventDAO {
    void addEvent(Event event);
}
