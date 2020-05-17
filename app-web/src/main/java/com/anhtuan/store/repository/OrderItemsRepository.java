package com.anhtuan.store.repository;

import com.anhtuan.store.model.OrderEntity;
import com.anhtuan.store.model.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItemEntity, Integer> {
}
