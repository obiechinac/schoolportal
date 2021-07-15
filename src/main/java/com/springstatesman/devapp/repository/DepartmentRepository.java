package com.springstatesman.devapp.repository;

import com.springstatesman.devapp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by HP on 1/15/2021.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {


//    @Query("select c.deptName , p.courseName from Department c join c.courses p")
//    public String getDeptCourses();
}
