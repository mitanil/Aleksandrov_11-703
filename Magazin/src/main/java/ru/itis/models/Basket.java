package ru.itis.models;


import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Basket {
    User user;
    List<Product> products;
    Long id;
}
