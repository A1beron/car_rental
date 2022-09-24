package com.books.api.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String buildingNo;
    private String apartmentNo;
    private String postalCode;

}
