package com.springstatesman.devapp.repository;

import com.springstatesman.devapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HP on 1/26/2021.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule> findAllByCourseIs(Long courseId);
}
