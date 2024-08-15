package com.ngfrt.appmain.service;

import com.ngfrt.appmain.model.dto.UserAccountDTO;
import com.ngfrt.appmain.model.dto.UserRegisterDTO;
import com.ngfrt.appmain.model.entity.User;
import com.ngfrt.appmain.model.mapper.UserMapper;
import com.ngfrt.appmain.repository.UserRepository;
import com.ngfrt.appmain.repository.UserRoleRepository;
import com.ngfrt.appmain.service.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userRoleRepository = userRoleRepository;
    }

    public void registerUser(UserRegisterDTO userRegisterDTO) {
        //TODO send activation email
        User user = userMapper.registerDtoToEntity(userRegisterDTO, passwordEncoder, userRoleRepository);
        userRepository.save(user);
    }

    public UserAccountDTO getUserAccountInfo(String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Error retrieving User information!"));

        return userMapper.entityToAccountDTO(user);
    }

    public boolean editUser(UserAccountDTO newUserDetails, String currentEmail, HttpServletRequest request){
        User userEntity = userRepository.findByEmail(currentEmail).orElseThrow(()-> new UserNotFoundException("Error while retrieving user."));
        String newEmail = newUserDetails.getEmail();

        // Otherwise have to manually implement entity to entity mapping in the mapper and to ignore all non-mapped fields.
        User updatedEntity = userMapper.accountDtoToEntity(newUserDetails, userEntity);
        userRepository.save(updatedEntity);
        userRepository.findByEmail(newUserDetails.getEmail()).orElseThrow(()-> new UserNotFoundException("Edit operation failed!"));

        if (!newEmail.equals(currentEmail)) {
            //TODO - send new confirmation email
            return true;
        }
        return false;
    }

    public UUID getUserUuidByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("No such user could be found")).getUuid();
    }
}
