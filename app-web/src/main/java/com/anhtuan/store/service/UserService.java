package com.anhtuan.store.service;

import com.anhtuan.store.dto.request.PasswordResetDto;
import com.anhtuan.store.dto.request.UserRegisterRqDto;
import com.anhtuan.store.dto.response.UserDto;
import com.anhtuan.store.model.UserEntity;

public interface UserService {
    void registerUser(UserRegisterRqDto dto);

    void resetPassword(PasswordResetDto passwordResetDto, UserEntity userEntity);

    UserDto getById(Integer id);
}
