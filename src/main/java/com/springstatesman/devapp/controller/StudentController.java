package com.springstatesman.devapp.controller;

import com.springstatesman.devapp.entity.Student;
import com.springstatesman.devapp.model.EditPictureModel;
import com.springstatesman.devapp.model.StudentRegistrationModel;
import com.springstatesman.devapp.service.CourseService;
import com.springstatesman.devapp.service.ProgramService;
import com.springstatesman.devapp.service.StudentService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Iterator;
import java.util.UUID;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hp on 4/29/2021.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProgramService programService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/studentForm")
    public String studentForm(Model model) {
        model.addAttribute("student", new StudentRegistrationModel());
        return "student/studentForm";

    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") StudentRegistrationModel student) {
        studentService.saveStudent(student);
        return "redirect:/studentForm";
    }
    @GetMapping("/selectCourses")
    public String selectCourses(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("courses",this.courseService.getAllCourses());

        return "student/chooseCourses";
    }
    @PostMapping("/chooseCourses")
    public RedirectView saveChosenCourses(@ModelAttribute Student student,Authentication authentication){

        String username = authentication.getName();
       Student student1 = this.studentService.findByUserName(username);
        student1.setCourses(student.getCourses());

        this.studentService.saveEditStudent(student1);
        return new RedirectView("/index",true);
    }
    @GetMapping("/editStudent")
    public String editLecturerForm(@RequestParam long stdId, Model model, Authentication authentication) {
        Student student;
        String username = authentication.getName();
         student = this.studentService.findByUserName(username);
        System.out.println(" before Editing"+student);
        model.addAttribute("student", student);
        model.addAttribute("programs",this.programService.findAll());
        model.addAttribute("editPectureModel", new EditPictureModel());

        return "student/editStudentForm";
    }

    @PostMapping("/saveEditStudent")
    public String saveEditLecturer(@ModelAttribute Student student,Authentication authentication){
        String username = authentication.getName();
        Student student1 = this.studentService.findByUserName(username);
        System.out.println(student1);
        student.setAdmissionNumber(student1.getAdmissionNumber());
        student.setDateOfAdmission(student1.getDateOfAdmission());
        student.setUsers(student1.getUsers());
        student.setStdId(student1.getStdId());
        student.setGender(student1.getGender());
        this.studentService.saveEditStudent(student);
        return "redirect:/index";
    }

    @GetMapping("/students")
    public String allStudents(Model model, @PageableDefault(size = 5) Pageable pageable) {

        model.addAttribute("students", this.studentService.findAllStudents(pageable));
        return "student/students";
    }

    @GetMapping("/students/{id}")
    public String getStudentById(@PathVariable long id,Model model){
        model.addAttribute("student",this.studentService.getById(id));

        return "student/student";
    }


    @GetMapping("/studentPicture")
    public ResponseEntity<EditPictureModel> getPicture(Authentication authentication,Model model) {
        String usersname = authentication.getName();
        Student student = this.studentService.findByUserName(usersname);
        EditPictureModel editPictureModel = this.studentService.getPictureModelByUserId(student.getStdId());
        editPictureModel.setId(student.getStdId());
        editPictureModel.setPicturePath("/mm_pics/" + student.getPicturePath());
        model.addAttribute("picture",editPictureModel);
        return ResponseEntity.ok(editPictureModel);
    }

// this is an alternative method
//    @PostMapping("/saveStudentImage")
//    public RedirectView savePix(@ModelAttribute EditPictureModel multipartFile, Authentication authentication)throws IOException{
//      //  System.out.println("File came " + !(multipartFile.getFileBody().isEmpty()));
//        System.out.println("Original file name: " + multipartFile.getFileBody().getOriginalFilename());
//        String imageFolderPath = "C:/dabs_mm_pics/student_pic/";
//   //     Generating unique random name for the picture so it wouldn't override other with the same name.
//        UUID randomName = UUID.randomUUID();
//        String imageFormat = FilenameUtils.getExtension(multipartFile.getFileBody().getOriginalFilename());
//        String newName = randomName + "." + imageFormat;
//        String filepath = imageFolderPath + newName;
//        File finalDetination = new File(filepath);
//        try {
//            multipartFile.getFileBody().transferTo(finalDetination);
//        }catch (IOException e){e.printStackTrace();}
//
//
//        String username = authentication.getName();
//        Student student = this.studentService.findByUserName(username);
//
//        System.out.println(">>>>> saving picture for student");
//        EditPictureModel pictureModel = new EditPictureModel();
//        pictureModel.setId(student.getStdId());
//        pictureModel.setPicturePath(newName);
//        this.studentService.saveProfilePicture(pictureModel);
//        return new RedirectView("/editStudent",true);
//    }
//
//
//    @PostMapping("/saveStudentPicture")
//    @ResponseBody
//    public String addPicture(MultipartHttpServletRequest request, Authentication authentication) {
//        Iterator<String> iterator = request.getFileNames();
//        System.out.println(">>>>>> saving picture");
//        String imageFolderPath = "C:/dabs_mm_pics/student_pic/";
//        MultipartFile picture = request.getFile(iterator.next());
//        if (picture.isEmpty()) {
//            return "Error! File Requested is Empty";
//        }
//        //Generating unique random name for the picture so it wouldn't override other with the same name.
//        UUID randomName = UUID.randomUUID();
//        String imageFormat = FilenameUtils.getExtension(picture.getOriginalFilename());
//        String newName = randomName + "." + imageFormat;
//        String filepath = imageFolderPath + newName;
//        File fileDestination = new File(filepath);
//        try {
//            picture.transferTo(fileDestination);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String username = authentication.getName();
//        Student student = this.studentService.findByUserName(username);
//
//        EditPictureModel pictureModel = new EditPictureModel();
//        pictureModel.setId(student.getStdId());
//        pictureModel.setPicturePath(newName);
//        this.studentService.saveProfilePicture(pictureModel);
//        return "success";
//    }
}
