package com.anhtuan.store.service;

import com.anhtuan.store.dto.request.ForgotPasswordTemplateDto;
import com.anhtuan.store.model.PasswordResetTokenEntity;

public interface PasswordResetTokenService {
    ForgotPasswordTemplateDto createForgotPasswordTemplateDto(String email, String baseUrl);

    PasswordResetTokenEntity checkToken(String token);
}
