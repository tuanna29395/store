package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.constants.Commons;
import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.Role;
import com.anhtuan.store.commons.enums.TypeLoginFlag;
import com.anhtuan.store.dto.request.PasswordResetDto;
import com.anhtuan.store.dto.request.UserRegisterRqDto;
import com.anhtuan.store.dto.response.UserDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.PasswordResetTokenEntity;
import com.anhtuan.store.model.RoleEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.PasswordResetTokenRepository;
import com.anhtuan.store.repository.RoleRepository;
import com.anhtuan.store.repository.UserRepository;
import com.anhtuan.store.service.CommonService;
import com.anhtuan.store.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private CommonService commonService;

    private final String DEFAULT_PROFILE_IMAGE = "/images/profile.png";
    private final String PATH_PROFILE_IMAGE = "/images/";
    public static String UPLOAD_IMAGE_PROFILE_DIR = System.getProperty("user.dir") + "\\app-web\\src\\main\\resources\\static\\images\\";

    @Override
    public void registerUser(UserRegisterRqDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserEntity user = modelMapper.map(dto, UserEntity.class);
        RoleEntity role = roleRepository.findByIdAndDeleteFlagIsNull(Role.CUSTOMER.getVal()).orElseThrow(() -> Exception.dataNotFound()
                .build(ErrorMessage.Role.ROLE_NOT_FOUND
                        .replace(Commons.ID, String.valueOf(Role.CUSTOMER.getVal()))));
        user.setRole(role);
        user.setDeletedFlag(DeleteFlag.NOT_DELETE.getVal());

        userRepository.save(user);
    }

    @Override
    public void resetPassword(PasswordResetDto passwordResetDto, UserEntity userEntity) {
        PasswordResetTokenEntity tokenEntity = passwordResetTokenRepository.findByToken(passwordResetDto.getToken());
        passwordResetTokenRepository.delete(tokenEntity);
        userEntity.setPassword(passwordEncoder.encode(passwordResetDto.getConfirmPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public UserDto getById(Integer id) {
        return transformUserDto(userRepository.findById(id).get());
    }

    @Override
    public void update(Integer userId, UserDto dto) {
        UserEntity userEntity = userRepository.findById(userId).get();
        userEntity.setUsername(dto.getUsername());
        userEntity.setFullName(dto.getFullName());
        userEntity.setAddress(dto.getAddress());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        if (Objects.nonNull(dto.getFile())) {
            try {
                String imageUrl = commonService.saveImageToFolder(dto.getFile(), UPLOAD_IMAGE_PROFILE_DIR);
                userEntity.setAvatar(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userRepository.save(userEntity);
    }

    private UserDto transformUserDto(UserEntity userEntity) {

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        if (!Objects.nonNull(userEntity.getAvatar())) {
            userDto.setAvatar(DEFAULT_PROFILE_IMAGE);
        } else {

            if (!userEntity.getTypeLogin().equals(TypeLoginFlag.FACEBOOK_LOGIN.getVal()) || !userEntity.getAvatar().contains("http")) {
                userDto.setAvatar(PATH_PROFILE_IMAGE + userEntity.getAvatar());
            }
        }

        return userDto;
    }
}
