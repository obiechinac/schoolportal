package com.springstatesman.devapp.service;

import com.springstatesman.devapp.entity.Program;
import com.springstatesman.devapp.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HP on 1/29/2021.
 */
@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programeRepository;

    public Page<Program> findAllPrograme(Pageable pageable){
        return this.programeRepository.findAll(pageable);
    }

    public List<Program> findAll(){return this.programeRepository.findAll();}

    public Program getOneById(long id){return this.programeRepository.getOne(id);}

    public void deleteById(long id){this.programeRepository.deleteById(id);}
    public void savePrograme(Program programe){this.programeRepository.save(programe);}


    public void saveEditProgram(Program program) {
        Program program1 = this.programeRepository.getOne(program.getProgrameId());

    }
}
