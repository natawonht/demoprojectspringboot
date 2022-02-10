package com.ais.demoproject.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {

    @Id
    String userName;
    String firstName;
    String lastName;
    String email;

}




