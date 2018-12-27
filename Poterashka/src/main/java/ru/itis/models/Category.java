package ru.itis.models;


import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Category {

    private String name;
    private Integer id;
}
