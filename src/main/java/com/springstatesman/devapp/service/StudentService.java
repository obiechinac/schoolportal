package com.springstatesman.devapp.service;

import com.springstatesman.devapp.entity.Student;
import com.springstatesman.devapp.entity.Users;
import com.springstatesman.devapp.model.EditPictureModel;
import com.springstatesman.devapp.model.StudentRegistrationModel;
import com.springstatesman.devapp.model.UsersegistrationModel;
import com.springstatesman.devapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by HP on 4/28/2021.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UsersServiceImpl usersService;

    public void saveStudent(StudentRegistrationModel student) {
        Users users = this.registerStudentUser(student);

        Date date = Date.from(Instant.now());

        Student student1 = new Student();
//        String level = date-student.getDateOfAdmission();
        student1.setAdmissionNumber(student.getAdmissionNumber());
        student1.setDateOfBirth(student.getDateOfBirth());
        student1.setFirstName(student.getFirstName());
        student1.setSecondName(student.getSecondName());
        student1.setSurName(student.getSurName());
        student1.setTelephoneNumber(student.getTelephoneNumber());
        student1.setGender(student.getGender());
        student1.setUsers(users);
        this.studentRepository.save(student1);

    }
    public void saveEditStudent(Student student){
        this.studentRepository.save(student);
    }


    public Users registerStudentUser(StudentRegistrationModel student) {
        UsersegistrationModel registrationModel = new UsersegistrationModel();

        registrationModel.setUsername(student.getUsername());
        registrationModel.setPassword(student.getPassword());
        registrationModel.setGender(student.getGender());
        registrationModel.setConfirmPassword(student.getConfirmPassword());
        registrationModel.setTelephoneNumber(student.getTelephoneNumber());
        registrationModel.setEmail(student.getEmail());
//        registrationModel.setGender(student.getGender());
        String DEFAULT_ROLE = "STUDENT";
        registrationModel.setRoles(DEFAULT_ROLE);
        registrationModel.setPermissions(student.getPermissions());
        return this.usersService.saveUser(registrationModel);
    }

    public EditPictureModel getPictureModelByUserId(long id){
        Student student = this.studentRepository.getOne(id);
        EditPictureModel editPictureModel = new EditPictureModel();
        editPictureModel.setId(student.getStdId());
        editPictureModel.setPicturePath(student.getPicturePath());
        return editPictureModel;
    }

    public void saveProfilePicture(EditPictureModel editPictureModel){
        Student student = this.studentRepository.getOne(editPictureModel.getId());
        student.setPicturePath(editPictureModel.getPicturePath());
        this.studentRepository.saveAndFlush(student);
    }

    public Page<Student> findAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Student getById(Long id) {
        return studentRepository.getOne(id);
    }


    public Student findByUserName(String usersname) {
        Student student = this.studentRepository.findByUsers_Username(usersname);
        return student;
    }
}
