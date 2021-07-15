package com.springstatesman.devapp.repository;

import com.springstatesman.devapp.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by HP on 1/15/2021.
 */

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {
}
