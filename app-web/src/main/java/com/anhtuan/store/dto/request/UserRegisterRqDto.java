package com.anhtuan.store.dto.request;

import com.anhtuan.store.commons.constants.Commons;
import com.anhtuan.store.commons.constants.Messages;
import com.anhtuan.store.validation.FieldsValueMatch;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@FieldsValueMatch(
        field = "password",
        fieldMatch = "passwordConfirmation",
        message = Messages.User.PASSWORD_CONFIRMATION_MATCH_REQUIRED
)
public class UserRegisterRqDto {
    @Pattern(regexp = Commons.EMAIL_PATTERN, message = Messages.User.EMAIL_INVALID)
    private String email;

    @NotEmpty(message = Messages.User.USER_NAME_REQUIRED)
    private String userName;

    @NotEmpty(message = Messages.User.PASSWORD_REQUIRED)
    private String password;

    @NotEmpty(message = Messages.User.RE_PASSWORD_REQUIRED)
    private String passwordConfirmation;
}
