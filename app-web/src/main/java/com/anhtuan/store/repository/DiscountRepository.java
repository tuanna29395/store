package com.anhtuan.store.repository;

import com.anhtuan.store.model.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface DiscountRepository extends JpaRepository<DiscountEntity, Integer>, QuerydslPredicateExecutor<DiscountEntity> {
    DiscountEntity findByIdAndDeleteFlag(Integer id, Integer deleteFlag);

    List<DiscountEntity> findAllByDeleteFlag(Integer deleteFlag);
}
