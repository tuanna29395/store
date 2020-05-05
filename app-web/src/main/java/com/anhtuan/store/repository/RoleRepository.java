package com.anhtuan.store.repository;

import com.anhtuan.store.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByIdAndDeleteFlagIsNull(Integer id);
}
