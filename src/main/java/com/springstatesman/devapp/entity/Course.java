package com.springstatesman.devapp.entity;

import com.springstatesman.devapp.entity.enums.Venues;
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
//@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private String courseCode;
    private double creditUnit;
    private int duration;


    @ManyToMany(fetch= FetchType.LAZY,cascade= {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "lecturer_course",
            joinColumns= @JoinColumn(name = "course_id"),
            inverseJoinColumns= @JoinColumn(name = "lecturer_id")
    )
    private List<Lecturer> lecturers = new ArrayList<>();

    @ManyToOne
    private Program programe;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

   @Enumerated(EnumType.STRING)
    private Venues venues;

    @OneToMany(mappedBy = "course")
    private List<Schedule> schedules = new ArrayList<>();

    @ManyToOne
    private Department department;

    public Course(String courseName,int duration,String courseCode,double creditUnit){
        this.courseName=courseName;
        this.duration=duration;
        this.courseCode=courseCode;
        this.creditUnit=creditUnit;

    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", creditUnit=" + creditUnit +
                ", duration=" + duration +
                ", lecturers=" + lecturers +
                '}';
    }
}
