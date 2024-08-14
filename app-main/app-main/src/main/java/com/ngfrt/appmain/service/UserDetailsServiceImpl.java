package com.ngfrt.appmain.service;

import com.ngfrt.appmain.model.entity.User;
import com.ngfrt.appmain.model.entity.UserRoleEntity;
import com.ngfrt.appmain.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .map(this::entityToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private UserDetails entityToUserDetails(User entity) {
        return org.springframework.security.core.userdetails.User
                .withUsername(entity.getEmail())
                .password(entity.getPassword())
                .authorities(entity.getRoles().stream().map(this::mapAuthorities).toList())
                .build();
    }

    private GrantedAuthority mapAuthorities(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
    }
}
