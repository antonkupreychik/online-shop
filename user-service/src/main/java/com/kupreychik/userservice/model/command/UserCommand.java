package com.kupreychik.userservice.model.command;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class UserCommand implements Serializable {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}