package com.springstatesman.devapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by HP on 4/28/2021.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Student  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stdId;

    private String surName;
    private String firstName;
    private String secondName;
    private String admissionNumber;

    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String telephoneNumber;
    private String picturePath;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfAdmission;


    @OneToOne
    private Users users;

    @ManyToOne
    private Program program;

    @ManyToMany
    private List<Course> courses;

    @ManyToMany
    private List<Schedule> schedules;

    @Override
    public String toString() {
        return "Student{" +
                "stdId=" + stdId +
                ", surName='" + surName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", admissionNumber='" + admissionNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", dateOfAdmission=" + dateOfAdmission +
                ", users=" + users +
                '}';
    }
}
