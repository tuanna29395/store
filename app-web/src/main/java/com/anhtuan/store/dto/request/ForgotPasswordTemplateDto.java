package com.anhtuan.store.dto.request;

import lombok.Data;

@Data
public class ForgotPasswordTemplateDto {
    private String email;
    private String resetPasswordUrl;
}
