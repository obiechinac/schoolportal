package com.springstatesman.devapp.repository;

import com.springstatesman.devapp.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HP on 1/20/2021.
 */
@Repository
public interface LecturerRepository extends JpaRepository<Lecturer,Long> {
    List<Lecturer> getAllByCoursesIs(String courseName);
    List<Lecturer> getLecturersBySchedulesIs(long id);
    Lecturer findByUsers_Username(String username);
}
