package com.anhtuan.store.repository;

import com.anhtuan.store.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findByUserId(Integer userId);

    @Query(value = "select sum(amount) from order_items ot where order_id = ?1 group by order_id", nativeQuery = true)
    Long calculateAmountById(Integer orderId);
}
