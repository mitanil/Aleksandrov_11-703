package ru.itis.models;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Building {

    private String buildingName;
    private String address;
    private Integer id;

}
