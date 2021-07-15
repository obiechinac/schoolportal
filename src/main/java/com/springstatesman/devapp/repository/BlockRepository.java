package com.springstatesman.devapp.repository;

import com.springstatesman.devapp.entity.Block;
import com.springstatesman.devapp.entity.enums.Venues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HP on 1/20/2021.
 */
@Repository
public interface BlockRepository extends JpaRepository<Block,Long> {

    List<Block> findAllByVenuesIs(Venues venues);
}
