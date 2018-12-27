package ru.itis.gett_taxi.models;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;

    private User client;
    private Driver driver;
    private Car car;
    private String address;
}
