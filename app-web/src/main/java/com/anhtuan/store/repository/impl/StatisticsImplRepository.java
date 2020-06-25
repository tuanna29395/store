package com.anhtuan.store.repository.impl;

import com.anhtuan.store.repository.StatisticsRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
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

    private static final String REPORT_REVENUE = "select o.id order_id , p.id product_id , p.name product_name , oi.sold_price, size.name size_name , oi.size_price , oi.percent_discount , oi.amount,oi.quantity\n" +
            "from store.orders o\n" +
            "         join order_items oi on o.id = oi.order_id\n" +
            "         left join size on oi.size_id = size.id\n" +
            "         join product p on p.id = oi.product_id\n" +
            "where o.status = 2";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> revenueStatisticsByMonth() {
        Query query = entityManager.createNativeQuery(REVENUE_STATISTICS_BY_MONTH);
        return query.getResultList();
    }

    @Override
    public List<Object[]> reportRevenue(Date from, Date to) {
        String condition = "";
        if (from != null && to != null) condition = "and o.updated_at BETWEEN from and to";
        Query query = entityManager.createNativeQuery(REPORT_REVENUE + condition);
        return query.getResultList();
    }
}
