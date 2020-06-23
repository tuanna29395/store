package com.anhtuan.store.controller.api;

import com.anhtuan.store.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/statistic")
public class StatisticApiController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<?> revenueStatisticsByMonth() {
        return ResponseEntity.ok(statisticsService.getRevenueStatisticsByMonth());
    }
}
