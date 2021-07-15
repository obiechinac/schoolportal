package com.springstatesman.devapp.repository;

import com.springstatesman.devapp.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HP on 1/20/2021.
 */

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findAllByPrograme(long id);

}
