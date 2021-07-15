package com.springstatesman.devapp.controller;
import com.springstatesman.devapp.entity.Log;
import com.springstatesman.devapp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Hp on 04/29/2021.
 */
@Controller
@RequestMapping("/log")
public class LogController {
    private LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/log")
    public String getLog(Model model, @PageableDefault(size = 8) Pageable pageable) {
        Page<Log> log = this.logService.getAll(pageable);
        model.addAttribute("log", log);

        return "admin/log";
    }
}
