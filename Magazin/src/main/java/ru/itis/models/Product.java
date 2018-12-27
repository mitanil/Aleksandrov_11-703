package ru.itis.models;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product{
    Long id;
    String name;
}
