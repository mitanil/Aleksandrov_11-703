package ru.itis.models;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Client {

    private String name;
    private String login;
    private String hashPassword;
    private String rawPassword;
    private Integer clientId;
}
