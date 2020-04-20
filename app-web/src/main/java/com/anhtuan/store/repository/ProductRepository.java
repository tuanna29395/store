package com.anhtuan.store.repository;

import com.anhtuan.store.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Integer countByCategoryIdAndStatusEquals(Integer categoryId, Integer status);
}
