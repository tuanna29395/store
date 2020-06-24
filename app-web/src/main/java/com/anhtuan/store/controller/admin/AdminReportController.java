package com.anhtuan.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin/report")
public class AdminReportController {
    @GetMapping
    public String report(){
        return "admin-report/report-order";
    }
}
