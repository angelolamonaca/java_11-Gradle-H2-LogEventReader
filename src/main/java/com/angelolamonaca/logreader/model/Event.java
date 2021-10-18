package com.angelolamonaca.logreader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Event {
    @NonNull
    private Long id;
    @NonNull
    private Integer duration;
    private String type;
    private String host;
    private Boolean alert;
}
