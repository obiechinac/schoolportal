package com.springstatesman.devapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;

/**
 * Created by HP on 4/28/2021.
 */
@Getter
@Setter
public class UsersegistrationModel {

    private String username;
    private String password;
    private String roles;
    private String permissions;

    private String gender;
    @Transient
    private String confirmPassword;
    private String email;
    private String telephoneNumber;

}
