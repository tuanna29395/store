package com.anhtuan.store.facebook.controller;

import com.anhtuan.store.commons.constants.Commons;
import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.Role;
import com.anhtuan.store.commons.enums.TypeLoginFlag;
import com.anhtuan.store.dto.response.UserFbDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.facebook.ulti.RestFB;
import com.anhtuan.store.model.RoleEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.RoleRepository;
import com.anhtuan.store.repository.UserRepository;
import com.anhtuan.store.service.AuthenticationServiceImpl;
import com.restfb.types.Photo;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
public class FacebookController {

    @Autowired
    private RestFB restFb;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @RequestMapping("/login-facebook")
    public String loginFacebook(HttpServletRequest request) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            return "redirect:/login?facebook=error";
        }

        String accessToken = restFb.getToken(code);
        UserFbDto user = restFb.getUserInfo(accessToken);
        UserEntity userEntity = userRepository.findByUserAppIdAndTypeLogin(user.getUserAppId(), TypeLoginFlag.FACEBOOK_LOGIN.getVal());
        if (!Objects.nonNull(userEntity)) {
            userEntity = createUserLoginFacebook(user);
        }
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(userEntity.getRole());
        UserDetails userDetail = authenticationService.buildUserForAuthentication(userEntity, authenticationService.getUserAuthority(roles));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
                userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/products";

    }

    private UserEntity createUserLoginFacebook(UserFbDto user) {

        UserEntity userEntity = new UserEntity();
        userEntity.setDeletedFlag(DeleteFlag.NOT_DELETE.getVal());
        userEntity.setPassword("");
        userEntity.setUsername(user.getUserName());
        userEntity.setAvatar(user.getAvatar());
        userEntity.setTypeLogin(TypeLoginFlag.FACEBOOK_LOGIN.getVal());
        userEntity.setUserAppId(user.getUserAppId());

        RoleEntity role = roleRepository.findByIdAndDeleteFlagIsNull(Role.CUSTOMER.getVal()).orElseThrow(() -> Exception.dataNotFound()
                .build(ErrorMessage.Role.ROLE_NOT_FOUND
                        .replace(Commons.ID, String.valueOf(Role.CUSTOMER.getVal()))));
        userEntity.setRole(role);

        return userRepository.save(userEntity);
    }
}
