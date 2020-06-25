package com.anhtuan.store.service;

import com.anhtuan.store.dto.response.ChartDto;
import com.anhtuan.store.dto.response.ReportRevenueDto;

import java.util.Date;
import java.util.List;

public interface StatisticsService {
    List<ChartDto> getRevenueStatisticsByMonth();

    List<ReportRevenueDto> getDataReportRevenue(Date from, Date to);
}
