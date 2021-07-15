package com.springstatesman.devapp.service;

import com.springstatesman.devapp.entity.Course;
import com.springstatesman.devapp.entity.Lecturer;
import com.springstatesman.devapp.entity.Student;
import com.springstatesman.devapp.entity.Users;
import com.springstatesman.devapp.model.EditPictureModel;
import com.springstatesman.devapp.model.LecturerRegistrationModel;
import com.springstatesman.devapp.model.StudentRegistrationModel;
import com.springstatesman.devapp.model.UsersegistrationModel;
import com.springstatesman.devapp.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Created by HP on 4/30/2021.
 */
@Service
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private UsersServiceImpl usersService;


    public void saveLecturer(LecturerRegistrationModel lecturer) {
        Users users = this.createLecturerUser(lecturer);
        Lecturer lecturer1 = new Lecturer();
        lecturer1.setLecturerGrade(lecturer.getLecturerGrade());
        lecturer1.setLecturerTitle(lecturer.getLecturerTitle());
        lecturer1.setDateOfemployment(lecturer.getDateOfemployment());
        lecturer1.setDateOfBirth(lecturer.getDateOfBirth());
        lecturer1.setFirstName(lecturer.getFirstName());
        lecturer1.setSecondName(lecturer.getSecondName());
        lecturer1.setSurName(lecturer.getSurName());
        lecturer1.setEmploymentNumber(lecturer.getEmploymentNumber());
        lecturer1.setGender(lecturer.getGender());
        lecturer1.setTelephoneNumber(lecturer.getTelephoneNumber());
        lecturer1.setUsers(users);
        this.lecturerRepository.save(lecturer1);

    }
    public void saveEditLecturer(Lecturer lecturer){
        this.lecturerRepository.save(lecturer);
    }

    public Users createLecturerUser(LecturerRegistrationModel lecturer){
        UsersegistrationModel registrationModel = new UsersegistrationModel();
        registrationModel.setUsername(lecturer.getUsername());
        registrationModel.setPassword(lecturer.getPassword());
        registrationModel.setGender(lecturer.getGender());
        registrationModel.setEmail(lecturer.getEmail());
        registrationModel.setConfirmPassword(lecturer.getConfirmPassword());
        registrationModel.setTelephoneNumber(lecturer.getTelephoneNumber());
        String DEFAULT_ROLE = "LECTURER";
        registrationModel.setRoles(DEFAULT_ROLE);
        registrationModel.setPermissions(lecturer.getPermissions());
        return this.usersService.saveUser(registrationModel);
    }

    public void saveProfilePicture(EditPictureModel editPictureModel){
        Lecturer lecturer = this.lecturerRepository.getOne(editPictureModel.getId());
        lecturer.setPicturePath(editPictureModel.getPicturePath());
        this.lecturerRepository.saveAndFlush(lecturer);
    }


    public Lecturer findByUsername(String username){
        Lecturer lecturer = this.lecturerRepository.findByUsers_Username(username);
        return lecturer;
    }

    public EditPictureModel getEditModelByUserId(long id){
        Lecturer lecturer = this.lecturerRepository.getOne(id);
        EditPictureModel editPictureModel = new EditPictureModel();
        editPictureModel.setId(lecturer.getLecturerId());
        editPictureModel.setPicturePath(lecturer.getPicturePath());
        return editPictureModel;
    }

    public Page<Lecturer> findAllLecturers(Pageable pageable) {
        return lecturerRepository.findAll(pageable);
    }

    public Lecturer getById(Long id) {
        return lecturerRepository.getOne(id);
    }



}
