package com.angelolamonaca.logreader.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 19/10/2021
 */
@Data
@AllArgsConstructor
public class EventDetails {
    private Long duration;
    private String type;
    private String host;
    private boolean alert;
}
