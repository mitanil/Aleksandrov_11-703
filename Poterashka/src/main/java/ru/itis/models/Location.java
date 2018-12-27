package ru.itis.models;

import lombok.*;


@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Location {

    private Integer locationId;
    private Integer building;
    private Integer parentLocation;
    private String locationName;
}
