package com.angelolamonaca.logreader.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 18/10/2021
 */
@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @NonNull
    private String id;
    @NonNull
    private Long duration;
    private String type;
    private String host;
    private Boolean alert;
}
