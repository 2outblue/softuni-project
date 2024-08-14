package com.ngfrt.appmain.service;

import com.ngfrt.appmain.model.dto.UserRegisterDTO;
import com.ngfrt.appmain.model.entity.User;
import com.ngfrt.appmain.model.mapper.UserMapper;
import com.ngfrt.appmain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public void registerUser(UserRegisterDTO userRegisterDTO) {
        //TODO send activation email
        userRepository.save(userMapper.registerDtoToEntity(userRegisterDTO, passwordEncoder));
    }
}
