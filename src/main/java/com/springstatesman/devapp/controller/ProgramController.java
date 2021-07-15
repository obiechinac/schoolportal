package com.springstatesman.devapp.controller;

import com.springstatesman.devapp.entity.Course;
import com.springstatesman.devapp.entity.Department;
import com.springstatesman.devapp.entity.Faculty;
import com.springstatesman.devapp.entity.Program;
import com.springstatesman.devapp.service.CourseService;
import com.springstatesman.devapp.service.DepartmentService;
import com.springstatesman.devapp.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created by HP on 5/7/2021.
 */

@Controller
@RequestMapping("/program")
public class ProgramController {

    private ProgramService programeService;
    private CourseService courseService;
    private DepartmentService departmentService;

    @Autowired
    public ProgramController(ProgramService programeService, CourseService courseService,DepartmentService departmentService) {
        this.programeService = programeService;
        this.courseService = courseService;
        this.departmentService = departmentService;
    }

    @GetMapping("/programForm")
    public String programForm(Model model){
        List<Course> courses = this.courseService.getAllCourses();
        model.addAttribute("courses",courses);
        model.addAttribute("program",new Program());
        return "program/programForm";
    }

    @PostMapping("/saveProgram")
    public RedirectView saveProgram(@ModelAttribute Program program){

        programeService.savePrograme(program);

        return new RedirectView("/programForm",true);
    }

    @GetMapping("/programs")
    public String allPrograms(@PageableDefault(size = 5) Pageable pageable,Model model ){
        List<Program> programs = this.programeService.findAll();
        model.addAttribute("programs",programs);

        return "program/programs";
    }

    @GetMapping("/program")
    public String getProgramById(@RequestParam long id,Model model){
        Program program = this.programeService.getOneById(id);
        model.addAttribute("program",program);
        return "program/program";
    }

    @GetMapping("/editProgram")
    public String editProgramForm(@RequestParam long programeId,Model model){
        Program program = this.programeService.getOneById(programeId);
        List<Department> departments = this.departmentService.getAllDpt();
        model.addAttribute("departments",departments);
        model.addAttribute("program",program);
        return "program/editProgramForm";
    }

    @PostMapping("/saveEditProgram")
    public RedirectView saveEditedCourse(@ModelAttribute ("program") Program program){
        Program program1 = this.programeService.getOneById(program.getProgrameId());
        program.setProgrameId(program1.getProgrameId());
        programeService.savePrograme(program);
        return new RedirectView("/programs",true);
    }
}
