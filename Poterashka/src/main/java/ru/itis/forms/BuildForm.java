package ru.itis.forms;

import lombok.*;
import ru.itis.models.Building;
import ru.itis.models.Location;

import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class BuildForm {
    Building building;
    List<Location> locations;
}
