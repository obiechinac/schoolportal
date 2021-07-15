package com.springstatesman.devapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by HP on 1/20/2021.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecturerId;
    private String surName;
    private String firstName;
    private String secondName;
    private String lecturerTitle;
    private String picturePath;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfemployment;
    private String telephoneNumber;
    private String employmentNumber;
//    private String lecturerGrade;

    private String lecturerGrade;
    @OneToOne
    private Users users;

    @ManyToMany(fetch= FetchType.EAGER,cascade= {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "lecturer_course",
            joinColumns= @JoinColumn(name = "lecturer_id"),
            inverseJoinColumns= @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    @Override
    public String toString() {
        return "Lecturer{" +
                "lecturerId=" + lecturerId +
                ", surName='" + surName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lecturerTitle='" + lecturerTitle + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", dateOfemployment=" + dateOfemployment +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", lecturerGrade='" + lecturerGrade + '\'' +
                '}';
    }

    @ManyToMany()
    private List<Schedule> schedules;

    @ManyToOne
    private Program program;

    @ManyToOne
    private Department department;


}
