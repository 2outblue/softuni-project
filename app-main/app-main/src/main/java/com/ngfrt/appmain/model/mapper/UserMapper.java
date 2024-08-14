package com.ngfrt.appmain.model.mapper;

import com.ngfrt.appmain.model.dto.UserRegisterDTO;
import com.ngfrt.appmain.model.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "password", target = "password", qualifiedByName = "mapUserEntityPassword")
    @Mapping(target = "active", constant = "false")
    User registerDtoToEntity(UserRegisterDTO userRegisterDTO, @Context PasswordEncoder passwordEncoder);

    @Named("mapUserEntityPassword")
    static String mapUserEntityPassword(String rawPassword, @Context PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(rawPassword);
    }
}
