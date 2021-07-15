package com.springstatesman.devapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 1/15/2021.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    private String deptName;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Faculty faculty;


    @OneToMany(mappedBy = "department")
    private List<Program> programes = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    private List<Lecturer> lecturers;

    @OneToMany(mappedBy = "department")
    private List<Course> courses;


    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '}';
    }
}
