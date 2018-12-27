package ru.itis.models;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Order {

    private Integer orderId;
    private Integer itemId;
    private Integer finder;
    private Integer placeOfLost;
    private Integer destination;
    private String description;
    private Boolean close;
}
