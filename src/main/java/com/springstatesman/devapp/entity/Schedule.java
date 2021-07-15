package com.springstatesman.devapp.entity;

import com.springstatesman.devapp.entity.enums.DaysOfWeek;
import com.springstatesman.devapp.entity.enums.Periods;
import com.springstatesman.devapp.entity.enums.Venues;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by HP on 1/26/2021.
 */

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;


    @ManyToOne
    private Course course;

    DaysOfWeek daysOfWeek;

    Periods periods;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Lecturer> lecturers;

    @ManyToMany
    private List<Student> students;


    @Enumerated(EnumType.STRING)
    private Venues venues;


    @OneToOne
    private Block block;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Room room;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public DaysOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(DaysOfWeek daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public Periods getPeriods() {
        return periods;
    }

    public void setPeriods(Periods periods) {
        this.periods = periods;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Venues getVenues() {
        return venues;
    }

    public void setVenues(Venues venues) {
        this.venues = venues;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
        @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", course=" + course +
                ", daysOfWeek=" + daysOfWeek +
                '}';
    }


}
