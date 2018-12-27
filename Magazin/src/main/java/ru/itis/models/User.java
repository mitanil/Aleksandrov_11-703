package ru.itis.models;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    Long userId;
    String login;
    String rawPassword;
    String hashPassword;
}
