package ru.itis.models;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Item {

    private Integer itemId;
    private String itemName;
    private Integer category;
    private Integer owner;
    private String description;
    private String image;

    @Override
    public String toString(){
        return itemName + "\n" + description;
    }
}
