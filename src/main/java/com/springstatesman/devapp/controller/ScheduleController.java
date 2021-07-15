package com.springstatesman.devapp.controller;
import com.springstatesman.devapp.entity.Course;
import com.springstatesman.devapp.entity.Lecturer;
import com.springstatesman.devapp.entity.Schedule;
import com.springstatesman.devapp.entity.Student;
import com.springstatesman.devapp.entity.enums.Venues;
import com.springstatesman.devapp.service.CourseService;
import com.springstatesman.devapp.service.LecturerService;
import com.springstatesman.devapp.service.ScheduleService;
import com.springstatesman.devapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 1/26/2021.
 */

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;
    private StudentService studentService;
    private LecturerService lecturerService;
    private CourseService courseService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, StudentService studentService, LecturerService lecturerService, CourseService courseService) {
        this.scheduleService = scheduleService;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
        this.courseService = courseService;
    }


    @GetMapping("/generateSchedule")
    @ResponseBody
    public String generateSchedule(@RequestParam("courseId") Long id) {

        Course course = this.courseService.getById(id);
           System.out.println("course is  ..." + course);
        if (course.getSchedules().isEmpty()){
        this.scheduleService.generateSchedule(course);}
        else {return "already scheduled"; }

        return "scheduling complete";

    }

    @GetMapping("/studentSchedule")
    public String getStudentById(Authentication authentication, Model model){
        String username = authentication.getName();

        Student student = studentService.findByUserName(username);

        List<Course> courseList = student.getCourses();
        List<Lecturer> lecturers = new ArrayList<>();
        List<Schedule> schedules = this.scheduleService.getScheduleByStudentId(student.getStdId());

        for (Course course:courseList){
            schedules = this.courseService.getCourseSchedules(course.getCourseId());
            lecturers = course.getLecturers();
            System.out.println(">>>>>>          lecturers are >>>"+lecturers);

        }
        model.addAttribute("schedules",schedules);
        model.addAttribute("lecturers",lecturers);
        model.addAttribute("student",student);

        return"/student/studentSchedule";
    }


    @GetMapping("/lecturerSchedule")
    public String getLecturerSchedule(Authentication authentication, Model model) {

        String username = authentication.getName();
        Lecturer lecturer = this.lecturerService.findByUsername(username);
        model.addAttribute("schedules", this.scheduleService.getScheduleByLecturerId(lecturer.getLecturerId()));

        return "lecturer/lecturerSchedule";
    }

    @ResponseBody
    @GetMapping("/deleteAllSchedules")
    public String deleteAllSchedule(){
        List<Schedule> scheduleList = this.scheduleService.findAllSchedulea();
        this.scheduleService.deleteAll(scheduleList);

        return "success";
    }

    public void sendToModel(Model model, @PageableDefault(size = 8)Pageable pageable) {
        int pagesize = 8;
        model.addAttribute("venues", Venues.values());
        model.addAttribute("student", new Student());
        model.addAttribute("students", this.studentService.findAllStudents(pageable));

        model.addAttribute("lecturer", new Lecturer());
        model.addAttribute("lecturers", this.lecturerService.findAllLecturers(pageable));
        model.addAttribute("course", new Course());
        model.addAttribute("course1", this.courseService.getAllCourses(pageable));


    }
}
