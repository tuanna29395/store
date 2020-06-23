package com.anhtuan.store.repository.impl;

import com.anhtuan.store.repository.StatisticsRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StatisticsImplRepository implements StatisticsRepository {
    private static String REVENUE_STATISTICS_BY_MONTH = "select month(oi.created_at) month, sum(oi.amount)\n" +
            "from store.order_items oi\n" +
            "         join orders o on oi.order_id = o.id\n" +
            "where o.status = 2\n" +
            "group by month(oi.created_at)\n" +
            "\n" +
            "order by month asc";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> revenueStatisticsByMonth() {
        Query query = entityManager.createNativeQuery(REVENUE_STATISTICS_BY_MONTH);
        return query.getResultList();
    }
}
