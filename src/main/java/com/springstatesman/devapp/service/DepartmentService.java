package com.springstatesman.devapp.service;
import com.springstatesman.devapp.entity.Department;
import com.springstatesman.devapp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by HP on 1/16/2021.
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDpt(Department department){return departmentRepository.save(department);}
    public Optional<Department> findDptById(long id){return  departmentRepository.findById(id);
    }
    public void saveEdit(Department department){departmentRepository.save(department);}

    public List<Department> getAllDpt(){
        return departmentRepository.findAll();
    }

    public Department getOne(Long id) {return this.departmentRepository.getOne(id);
    }
}
