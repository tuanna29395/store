package com.anhtuan.store.repository;

import com.anhtuan.store.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderEntity, Integer> {
}
