package com.anhtuan.store.repository;

import com.anhtuan.store.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>, QuerydslPredicateExecutor<CategoryEntity> {
    CategoryEntity findByIdAndStatusNot(Integer categoryID, Integer deleteFlag);
}
