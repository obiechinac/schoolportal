package com.springstatesman.devapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by HP on 4/28/2021.
 */
@Setter
@Getter
public class StudentRegistrationModel {

//    private long stdId;
    private String username;
    private String password;
    private String roles;
    private String permissions;
    @Transient
    private String confirmPassword;
    private String email;
    private String surName;
    private String firstName;
    private String secondName;
    private String gender;
    private String admissionNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfAdmission;
    private String telephoneNumber;
}
