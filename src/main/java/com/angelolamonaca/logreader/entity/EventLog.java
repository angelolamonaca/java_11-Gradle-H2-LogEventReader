package com.angelolamonaca.logreader.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

/**
 * @author Angelo Lamonaca (https://www.angelolamonaca.com/)
 * @version 1.0
 * @since 19/10/2021
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class EventLog {
    @NonNull
    @JsonProperty
    private String id;

    @NonNull
    @JsonProperty
    private Timestamp timestamp;

    @JsonIgnore
    private String state;

    @JsonProperty
    private String type;

    @JsonProperty
    private String host;
}
