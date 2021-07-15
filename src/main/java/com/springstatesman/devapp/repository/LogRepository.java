package com.springstatesman.devapp.repository;

import com.springstatesman.devapp.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Hp on 14-dec-2020.
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
