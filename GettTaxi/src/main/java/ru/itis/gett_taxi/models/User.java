//package ru.itis.models;
//
//import lombok.*;
//
//import java.util.List;
//
//@AllArgsConstructor
//@Builder
//@Getter
//@Setter
//@NoArgsConstructor
//public class User {
//    private Long id;
//
//    private String name;
//
//    private String login;
//    private String password;
//
//    private List<Order> orders;
//}
package ru.itis.gett_taxi.models;

import lombok.*;

import java.util.List;

/**
 * 03.09.2018
 * User
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String email;
    private String hashPassword;
    private String rawPassword;

    private String firstName;
    private String lastName;
    private String address;

    private List<Order> orders;
}