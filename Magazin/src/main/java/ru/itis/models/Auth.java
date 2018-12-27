package ru.itis.models;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Auth {
    String uuid;
    Long userId;
}
