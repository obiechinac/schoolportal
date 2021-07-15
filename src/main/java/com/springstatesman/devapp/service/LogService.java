package com.springstatesman.devapp.service;
import com.springstatesman.devapp.entity.Log;
import com.springstatesman.devapp.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hp on 10/29/2020.
 */
@Service
public class LogService {
    private LogRepository logRepository;


    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void save(Log log) {
        this.logRepository.saveAndFlush(log);
    }

    public Page<Log> getAll(Pageable pageable) {
        Page<Log> log = this.logRepository.findAll(pageable);
        List<Log> logs = new ArrayList<>();
        for (Log tempLog : log) {
            logs.add(tempLog);
        }

        return (Page<Log>) new PageImpl(logs, pageable, log.getTotalElements());
    }
}
