package com.anhtuan.store.service;

import com.anhtuan.store.commons.enums.UserStatus;
import com.anhtuan.store.commons.values.Message;
import com.anhtuan.store.config.Principal;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmailAndDeletedFlg(username, UserStatus.NOT_DELETE.getValue())
            .orElseThrow(()->new UsernameNotFoundException(Message.USER_NOT_FOUND));
        return convertUserToPrincipal(userEntity);
}

    private UserDetails convertUserToPrincipal(UserEntity userEntity){
        Principal principal = new Principal();
        principal.setId(userEntity.getId());
        principal.setEmail(userEntity.getEmail());
        principal.setName(userEntity.getName());
        principal.setPassword(userEntity.getPassword());
        principal.setRole(userEntity.getRole());
        principal.setStatus(userEntity.getDeletedFlg());

        return principal;
    }
}
