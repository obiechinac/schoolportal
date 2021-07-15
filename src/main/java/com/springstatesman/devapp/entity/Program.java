package com.springstatesman.devapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 1/29/2021.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Program implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programeId;

    private String programName;

    @ManyToOne()
    private Department department;

    @OneToMany(mappedBy = "program")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "programe", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "program")
    private List<Lecturer> lecturers = new ArrayList<>();
}
