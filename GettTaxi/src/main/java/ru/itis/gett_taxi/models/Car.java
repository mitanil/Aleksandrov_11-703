package ru.itis.gett_taxi.models;


import lombok.*;
import ru.itis.gett_taxi.services.ConnectDB;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;

    private String name;

    private Driver owner;
}
