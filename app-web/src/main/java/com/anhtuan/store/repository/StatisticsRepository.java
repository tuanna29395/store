package com.anhtuan.store.repository;

import java.util.List;

public interface StatisticsRepository {
    List<Object[]> revenueStatisticsByMonth();
}
