package com.springstatesman.devapp.repository;

import com.springstatesman.devapp.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HP on 1/29/2021.
 */
public interface ProgramRepository extends JpaRepository<Program,Long> {
}
