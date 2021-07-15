package com.springstatesman.devapp.controller;

import com.springstatesman.devapp.entity.Course;
import com.springstatesman.devapp.entity.Department;
import com.springstatesman.devapp.entity.Faculty;
import com.springstatesman.devapp.entity.Program;
import com.springstatesman.devapp.service.CourseService;
import com.springstatesman.devapp.service.DepartmentService;
import com.springstatesman.devapp.service.FacultyService;
import com.springstatesman.devapp.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

/**
 * Created by HP on 1/16/2021.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProgramService programService;

    @GetMapping("/departmentForm")
    public String dptForm(Model model) {
        List<Faculty> faculties = facultyService.findAll();
        model.addAttribute("faculties", faculties);
        model.addAttribute("department", new Department());
        return "/department/departmentForm";

    }

    @PostMapping("/saveDpt")
    public RedirectView saveDpt(@ModelAttribute Department department, BindingResult bindingResult, Model model) {
            departmentService.saveDpt(department);
            return new RedirectView("/departmentForm",true);
        }

    @GetMapping("/department")
    public String getDpt(@RequestParam("deptId") Long id, Model model) {
        Department department = this.departmentService.getOne(id);
        model.addAttribute("department", department);
        Faculty faculty = this.facultyService.getOne(id);
        model.addAttribute("faculty", faculty);
        return "department/department";
    }

    @GetMapping("/editDeptForm")
    public String editDeptForm(@RequestParam("deptId") Long id, Model model) {
        Department department = this.departmentService.getOne(id);
        List<Faculty> faculties = this.facultyService.findAll();
        model.addAttribute("faculties", faculties);
        model.addAttribute("department", department);
        List<Program> programs = this.programService.findAll();

        return "department/editDeptForm";
    }

    @PostMapping("/saveEditDepartment")
    public RedirectView saveEditedDepartment(@ModelAttribute ("department") Department department){
        Department department1 = this.departmentService.getOne(department.getDeptId());
        department.setDeptId(department1.getDeptId());
        departmentService.saveDpt(department);
        return new RedirectView("/departments",true);
    }


    @GetMapping("/departments")
    public String allDpt(Model model) {
        List<Department> departments = departmentService.getAllDpt();
        model.addAttribute("departments", departments);
//        departments.forEach(System.out::print);
        return "/department/departments";
    }


    }
