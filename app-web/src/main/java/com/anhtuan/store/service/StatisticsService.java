package com.anhtuan.store.service;

import com.anhtuan.store.dto.response.ChartDto;
import com.anhtuan.store.dto.response.OrderReportDto;
import com.anhtuan.store.dto.response.ReportRevenueDto;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface StatisticsService {
    List<ChartDto> getRevenueStatisticsByMonth();

    List<ReportRevenueDto> getDataReportRevenue(Date from, Date to) throws ParseException;

    List<OrderReportDto> getDataReportOrder(Integer orderId) throws ParseException;
}
