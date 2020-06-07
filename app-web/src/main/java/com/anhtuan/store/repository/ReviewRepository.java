package com.anhtuan.store.repository;

import com.anhtuan.store.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer>, QuerydslPredicateExecutor<ReviewEntity> {

}
