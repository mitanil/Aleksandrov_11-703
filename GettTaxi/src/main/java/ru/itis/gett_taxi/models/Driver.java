package ru.itis.gett_taxi.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    private Long id;

    private String name;

    private String login;
    private String password;

    private List<Order> orders;

    private List<Car> cars;
}
