package com.anhtuan.store.service;

import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.enums.UserStatus;
import com.anhtuan.store.model.RoleEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmailAndDeletedFlag(username, UserStatus.NOT_DELETE.getValue())
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.User.USER_NOT_FOUND));
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(user.getRole());
        List<GrantedAuthority> authorities = getUserAuthority(roles);
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<RoleEntity> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (RoleEntity role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(UserEntity user, List<GrantedAuthority> authorities) {
        boolean active = user.getDeletedFlag() != 1;
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                active, true, true, true, authorities);
    }
}
