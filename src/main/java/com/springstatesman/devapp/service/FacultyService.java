package com.springstatesman.devapp.service;
import com.springstatesman.devapp.entity.Faculty;
import com.springstatesman.devapp.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by HP on 1/15/2021.
 */
@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty saveFaculty(Faculty faculty){return facultyRepository.save(faculty); }
    public List<Faculty> listFaculties(){return facultyRepository.findAll();}

    public void deleteFaculty(long id){
    facultyRepository.deleteById(id);}

    public Faculty getOne(Long id) { return this.facultyRepository.getOne(id);
    }

    public List<Faculty> findAll() { return this.facultyRepository.findAll();
    }
}
