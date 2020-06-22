package com.anhtuan.store.controller.admin;

import com.anhtuan.store.commons.constants.ViewHtmlConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/chart")
public class AdminChartController {
    @GetMapping
    public String chart() {
        return ViewHtmlConst.Charts.CHART_REVENUE;
    }
}
