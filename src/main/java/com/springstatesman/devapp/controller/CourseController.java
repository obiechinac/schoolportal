package com.springstatesman.devapp.controller;

import com.springstatesman.devapp.entity.Course;
import com.springstatesman.devapp.entity.Department;
import com.springstatesman.devapp.entity.Lecturer;
import com.springstatesman.devapp.entity.enums.Venues;
import com.springstatesman.devapp.service.CourseService;
import com.springstatesman.devapp.service.DepartmentService;
import com.springstatesman.devapp.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created by HP on 1/20/2021.
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LecturerService lecturerService;

    @GetMapping("/courseForm")
    public String addCourseForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("venues", Venues.FIRST_CAMPUS);
        model.addAttribute("venues2",Venues.SECOND_CAMPUS);

        return "course/courseForm";
    }

    @PostMapping("/saveCourse")
    public RedirectView saveCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);

        return new RedirectView("/courseForm",true);
    }

    @GetMapping("/allCourses")
    public String getAllCourses(Model model){
        List<Course> courses = this.courseService.getAllCourses();
        model.addAttribute("courses",courses);

        return "/course/courses";
    }

    @GetMapping("/courses")
    public String findAllCourses(@PageableDefault(size = 5)Pageable pageable, Model model){
        Page<Course> courses =  this.courseService.getAllCourses(pageable);
        model.addAttribute("courses",courses);
        return "course/courses";
    }

    @GetMapping("/editCourse")
    public String editCourse(@PageableDefault(size = 5)Pageable pageable,@RequestParam long courseId, Model model){
        Course course = this.courseService.getById(courseId);
        List<Department> departments = this.departmentService.getAllDpt();
        Page<Lecturer> lecturers = this.lecturerService.findAllLecturers(pageable);
        model.addAttribute("lecturers",lecturers);
        model.addAttribute("departments",departments);
        model.addAttribute("course",course);

        return "course/editCourseForm";
    }

    @PostMapping("/saveEditCourse")
    public RedirectView saveEditedCourse(@ModelAttribute Course course){
        Course course1 = this.courseService.getById(course.getCourseId());
        course.setCourseId(course1.getCourseId());
        courseService.saveCourse(course);
        return new RedirectView("/courses",true);
    }


}
