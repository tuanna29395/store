package com.anhtuan.store.dto.response;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String avatar;
    private String email;
    private MultipartFile file;

}
