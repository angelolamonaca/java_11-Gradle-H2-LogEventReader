package com.angelolamonaca.logreader.service;

import com.angelolamonaca.logreader.entity.Event;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
public interface EventService {
    Future<?> runThreadEventRegisterer(Event event);
    Future<?> runThreadEventRetriever();
    Event retrieveEvent();
}
