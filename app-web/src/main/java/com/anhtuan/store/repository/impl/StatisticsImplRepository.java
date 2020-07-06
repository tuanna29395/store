package com.anhtuan.store.repository.impl;

import com.anhtuan.store.commons.utils.DateTimeUtils;
import com.anhtuan.store.repository.StatisticsRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.ParseException;
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
    public List<Object[]> reportRevenue(Date from, Date to) throws ParseException {
        String condition = "";
        String queryString = REPORT_REVENUE;
        Query query;
        if (to == null) to = new Date();
        if (from == null) from = DateTimeUtils.defaultDate();

        condition = " and o.updated_at BETWEEN :startDate AND :endDate";
        query = entityManager.createNativeQuery(queryString + condition);
        query.setParameter("startDate", from, TemporalType.TIMESTAMP)
                .setParameter("endDate", to, TemporalType.TIMESTAMP);


        return query.getResultList();
    }
}
