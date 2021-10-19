package com.angelolamonaca.logreader.entity;

import lombok.*;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 19/10/2021
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EventLog {
    @NonNull
    private String id;
    @NonNull
    private EventLogState state;
    @NonNull
    private Integer timestamp;
    private String type;
    private String host;
}
