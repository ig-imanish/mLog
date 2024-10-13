package com.mlog.logger.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mlog.logger.service.LogService;

@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping
    public String getLogs(Model model) {
        model.addAttribute("logs", logService.getLogEntries());
        return "logs";
    }
}

