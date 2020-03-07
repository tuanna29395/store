package com.anhtuan.store.repository;

import com.anhtuan.store.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmailAndDeletedFlg(String username, Integer deletedFlg);
}
