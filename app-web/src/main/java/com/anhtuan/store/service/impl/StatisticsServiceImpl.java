package com.anhtuan.store.service.impl;

import com.anhtuan.store.dto.response.ChartDto;
import com.anhtuan.store.dto.response.ReportRevenueDto;
import com.anhtuan.store.repository.StatisticsRepository;
import com.anhtuan.store.service.StatisticsService;
import com.paypal.api.payments.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    StatisticsRepository statisticsRepository;

    @Override
    public List<ChartDto> getRevenueStatisticsByMonth() {
        List<ChartDto> data = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            ChartDto chartDto = new ChartDto();
            chartDto.setMonth(i);
            chartDto.setRevenue(0);
            data.add(chartDto);
        }
        List<ChartDto> resultTemp = new ArrayList<>();
        statisticsRepository.revenueStatisticsByMonth().forEach(item -> {
            ChartDto chartDto = new ChartDto();
            chartDto.setMonth((int) item[0]);
            chartDto.setRevenue(((BigDecimal) item[1]).intValue());
            resultTemp.add(chartDto);
        });

        for (ChartDto item : resultTemp) {
            data.get(item.getMonth() - 1).setRevenue(item.getRevenue());
        }
        return data;
    }

    @Override
    public List<ReportRevenueDto> getDataReportRevenue(Date from, Date to) {
        List<ReportRevenueDto> result = new ArrayList<>();
        statisticsRepository.reportRevenue(from, to).forEach(item -> {
            ReportRevenueDto dto = new ReportRevenueDto();
            dto.setOrderId((int) item[0]);
            dto.setProductId((int) item[1]);
            dto.setProductName((String) item[2]);
            dto.setSoldPrice(String.format("%,d",((BigDecimal)item[3]).intValue()));
            dto.setSizeName((String) item[4]);
            dto.setSizePrice(String.format("%,d",((BigDecimal)item[5]).intValue()));
            dto.setPercentDiscount((double) item[6]);
            dto.setAmount(String.format("%,d",((BigDecimal)item[7]).intValue()));
            dto.setQuantity((Integer) item[8]);
            result.add(dto);
        });
        return result;
    }
}
