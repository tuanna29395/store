package com.anhtuan.store.service;

import com.anhtuan.store.dto.response.ChartDto;

import java.util.List;

public interface StatisticsService {
    List<ChartDto> getRevenueStatisticsByMonth();
}
