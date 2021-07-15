package com.springstatesman.devapp.controller;

import com.springstatesman.devapp.entity.Faculty;
import com.springstatesman.devapp.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created by HP on 1/15/2021.
 */
@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/facultyForm")
    public String getFacultyForm(Model model){
        model.addAttribute("faculty",new Faculty());

        return "/faculty/facultyForm";
    }

    @PostMapping("/saveFaculty")
    public RedirectView saveFaculty(@ModelAttribute("faculty")Faculty faculty){

        facultyService.saveFaculty(faculty);

        return new RedirectView("/facultyForm",true);
    }

    public String getFacultyById(@ModelAttribute("id") long id,Model model){
        Faculty faculty = this.facultyService.getOne(id);
        model.addAttribute("faculty",faculty);
        return "faculty/faculty";

    }

    @GetMapping("/faculties")
    public String getAllFaculties(Model model){
      List<Faculty> faculties = facultyService.listFaculties();
        model.addAttribute("faculties", faculties);
//faculties.forEach(System.out::print);
        return "/faculty/faculties";
    }
}
