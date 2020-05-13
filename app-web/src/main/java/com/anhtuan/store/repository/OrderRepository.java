package com.anhtuan.store.repository;

import com.anhtuan.store.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findByUserId(Integer userId);
}
