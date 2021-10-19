package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.entity.EventMap;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
public interface EventService {
    void registerEvents(EventMap eventMap);
}
