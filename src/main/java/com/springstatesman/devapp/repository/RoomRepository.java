package com.springstatesman.devapp.repository;

import com.springstatesman.devapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by HP on 1/20/2021.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
