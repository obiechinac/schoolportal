package com.springstatesman.devapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Hp on 4/29/2020.
 */
@Controller
public class AccessController {
    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "error/403";
    }
}
