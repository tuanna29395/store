package com.anhtuan.store.repository;

import java.util.Date;
import java.util.List;

public interface StatisticsRepository {
    List<Object[]> revenueStatisticsByMonth();

    List<Object[]> reportRevenue(Date from, Date to);
}
