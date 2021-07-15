package com.springstatesman.devapp.repository;

import com.springstatesman.devapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by HP on 1/15/2021.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByUsers_Username(String username);


}
