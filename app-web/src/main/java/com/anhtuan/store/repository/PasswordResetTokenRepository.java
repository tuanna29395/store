package com.anhtuan.store.repository;

import com.anhtuan.store.model.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Integer> {
    PasswordResetTokenEntity findByToken(String token);
    PasswordResetTokenEntity findByUserId(Integer userId);


}
