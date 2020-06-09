package com.anhtuan.store.dto.request;

import com.anhtuan.store.commons.constants.Messages;
import com.anhtuan.store.validation.FieldsValueMatch;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "passwordConfirmation",
        message = Messages.User.PASSWORD_CONFIRMATION_MATCH_REQUIRED
)
@Data
public class PasswordResetDto {
    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;
}
