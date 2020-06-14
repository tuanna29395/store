package com.anhtuan.store.repository;

import com.anhtuan.store.model.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<DiscountEntity, Integer> {
    DiscountEntity findByIdAndDeleteFlag(Integer id, Integer deleteFlag);

    List<DiscountEntity> findAllByDeleteFlag(Integer deleteFlag);
}
