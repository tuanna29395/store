package com.anhtuan.store.repository;

import com.anhtuan.store.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>, QuerydslPredicateExecutor<CategoryEntity> {
}
