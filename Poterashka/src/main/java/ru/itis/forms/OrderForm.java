package ru.itis.forms;

import lombok.*;
import ru.itis.models.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class OrderForm {
    Order order;
    Item item;
    Location location;
    Building building;
    Client owner;
    Location locNow;
    Boolean close;
}
