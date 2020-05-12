package com.anhtuan.store.repository;

import com.anhtuan.store.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, QuerydslPredicateExecutor<ProductEntity> {
    Integer countByCategoryIdAndStatusEquals(Integer categoryId, Integer status);

    Optional<ProductEntity> findByIdAndAndDeleteFlagAndStatus(Integer productId, Integer deletedFlag, Integer status);
}
