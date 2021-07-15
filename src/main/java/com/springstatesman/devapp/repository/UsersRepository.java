package com.springstatesman.devapp.repository;

import com.springstatesman.devapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HP on 4/27/2021.
 */
public interface UsersRepository extends JpaRepository<Users,Long> {
   public Users findByUsername(String userName);
}
