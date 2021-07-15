package com.springstatesman.devapp.controller;

import com.springstatesman.devapp.entity.Lecturer;
import com.springstatesman.devapp.model.EditPictureModel;
import com.springstatesman.devapp.model.LecturerRegistrationModel;
import com.springstatesman.devapp.service.DepartmentService;
import com.springstatesman.devapp.service.LecturerService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.jws.WebParam;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by HP on 4/30/2021.
 */
@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/lecturerForm")
    public String getLecturerForm(Model model) {

        model.addAttribute("lecturer", new LecturerRegistrationModel());

        return "lecturer/lecturerForm";
    }

    @GetMapping("/editLecturer")
    public String editLecturerForm( @RequestParam long lecturerId,  Model model, Authentication authentication) {
//String role = authentication.getAuthorities().toString();
Lecturer lecturer;
//        System.out.println(role);
//        if (role.contains("ROLE_ADMIN")){
//            lecturer = this.lecturerService.getById(lecturerId);
//            System.out.println("lecturer id >>>> " +lecturerId);
//        }else {
//        String username = authentication.getName();
//         lecturer = this.lecturerService.findByUsername(username);}
        String username = authentication.getName();
        lecturer = this.lecturerService.findByUsername(username);

        model.addAttribute("editPictureModel",new EditPictureModel());
        model.addAttribute("departments",departmentService.getAllDpt());
        System.out.println(lecturer);
        model.addAttribute("lecturer", lecturer);
        return "lecturer/editLecturerForm";
    }

    @PostMapping("/saveEditLecturer")
    public RedirectView saveEditLecturer(@ModelAttribute Lecturer lecturer,Authentication authentication){
        String username = authentication.getName();
        System.out.println(lecturer.getLecturerId());
        Lecturer lecturer1 = this.lecturerService.findByUsername(username);
//        lecturer.setLecturerId(lecturer1.getLecturerId());
        lecturer.setGender(lecturer1.getGender());
        lecturer.setLecturerGrade(lecturer.getLecturerTitle());
        lecturer.setUsers(lecturer1.getUsers());
        lecturer.setEmploymentNumber(lecturer1.getTelephoneNumber());
        lecturer.setDateOfemployment(lecturer1.getDateOfemployment());
        this.lecturerService.saveEditLecturer(lecturer);
        return new RedirectView("/lecturers",true);
    }

    @PostMapping("/saveLecturer")
    public String saveLecturer(@ModelAttribute LecturerRegistrationModel lecturer) {

        this.lecturerService.saveLecturer(lecturer);

        return "redirect:/lecturerForm";
    }

    @GetMapping("/lecturerPicture")
    public ResponseEntity<EditPictureModel> getEditPictureModel(Authentication authentication) {
        String username = authentication.getName();
        Lecturer lecturer = this.lecturerService.findByUsername(username);
        EditPictureModel editPictureModel = new EditPictureModel();
        editPictureModel.setId(lecturer.getLecturerId());
        editPictureModel.setPicturePath(lecturer.getPicturePath());
        return ResponseEntity.ok(editPictureModel);
    }


    @PostMapping("/saveLecturerImage")
    public RedirectView savePix(@ModelAttribute EditPictureModel multipartFile, Authentication authentication)throws IOException{
        //  System.out.println("File came " + !(multipartFile.getFileBody().isEmpty()));
        System.out.println("Original file name: " + multipartFile.getFileBody().getOriginalFilename());
        String imageFolderPath = "C:/dabs_mm_pics/lecturer_pic/";
        //     Generating unique random name for the picture so it wouldn't override other with the same name.
        UUID randomName = UUID.randomUUID();
        String imageFormat = FilenameUtils.getExtension(multipartFile.getFileBody().getOriginalFilename());
        String newName = randomName + "." + imageFormat;
        String filepath = imageFolderPath + newName;
        File finalDetination = new File(filepath);
        try {
            multipartFile.getFileBody().transferTo(finalDetination);
        }catch (IOException e){e.printStackTrace();}


        String username = authentication.getName();
        Lecturer  lecturerle = this.lecturerService.findByUsername(username);

        System.out.println(">>>>> saving picture for lecturer");
        EditPictureModel pictureModel = new EditPictureModel();
        pictureModel.setId(lecturerle.getLecturerId());
        pictureModel.setPicturePath(newName);
        this.lecturerService.saveProfilePicture(pictureModel);
        return new RedirectView("/editLecturer",true);
    }

    @PostMapping("/saveLecturerPicture")
    @ResponseBody
    public String saveProfilePicture(MultipartHttpServletRequest request, Authentication authentication) {

        Iterator<String> iterator = request.getFileNames();
        String imageFolderPath = "C:/dabs_mm_pics/lecturer_pic/";
        MultipartFile picture = request.getFile(iterator.next());

        if (picture.isEmpty()) {
            return "error! file rquested is empty";
        }

        //Generating unique random name for the picture so it wouldn't override other with the same name.
        UUID randomName = UUID.randomUUID();
        String fileType = FilenameUtils.getExtension(picture.getOriginalFilename());
        String newName = randomName + "." + fileType;
        String filePath = imageFolderPath + newName;
        File fileDestination = new File(filePath);
        try {
            picture.transferTo(fileDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = authentication.getName();
        Lecturer lecturer = this.lecturerService.findByUsername(username);
        EditPictureModel editPictureModel = new EditPictureModel();
        editPictureModel.setId(lecturer.getLecturerId());
        editPictureModel.setPicturePath(newName);
        this.lecturerService.saveProfilePicture(editPictureModel);

        return "success";
    }


    @GetMapping("/lecturers")
    public String getAllLecturers(@PageableDefault(size = 5) Pageable pageable, Model model) {

        model.addAttribute("lecturers", this.lecturerService.findAllLecturers(pageable));

        return "lecturer/lecturers1";
    }

    @GetMapping("/lecturers/{id}")
    public String getLecturerById(@PathVariable long id, Model model) {

        model.addAttribute("lecturer", this.lecturerService.getById(id));
        return "lecturer/lecturer";
    }

}
