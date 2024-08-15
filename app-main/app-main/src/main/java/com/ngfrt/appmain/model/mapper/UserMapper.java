package com.ngfrt.appmain.model.mapper;

import com.ngfrt.appmain.model.dto.UserAccountDTO;
import com.ngfrt.appmain.model.dto.UserRegisterDTO;
import com.ngfrt.appmain.model.entity.User;
import com.ngfrt.appmain.model.entity.UserRoleEntity;
import com.ngfrt.appmain.model.entity.enums.UserRoleEnum;
import com.ngfrt.appmain.repository.UserRoleRepository;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "password", target = "password", qualifiedByName = "mapUserEntityPassword")
    @Mapping(target = "active", constant = "false")
    User registerDtoToEntity(UserRegisterDTO userRegisterDTO, @Context PasswordEncoder passwordEncoder, @Context UserRoleRepository userRoleRepository);

    UserAccountDTO entityToAccountDTO(User entity);

    @Mapping(source = "oldEntity.password", target = "password")
    @Mapping(source = "oldEntity.id", target = "id")
    @Mapping(source = "oldEntity.uuid", target = "uuid")
    @Mapping(source = "oldEntity.active", target = "active")
    @Mapping(source = "userAccountDTO.firstName", target = "firstName")
    @Mapping(source = "userAccountDTO.lastName", target = "lastName")
    @Mapping(source = "userAccountDTO.email", target = "email")
    @Mapping(source = "userAccountDTO.phone", target = "phone")
    User accountDtoToEntity(UserAccountDTO userAccountDTO, User oldEntity);

    @Named("mapUserEntityPassword")
    static String mapUserEntityPassword(String rawPassword, @Context PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(rawPassword);
    }

    @AfterMapping
    static void mapEntityUuidAndRole(@MappingTarget User userEntity, @Context UserRoleRepository userRoleRepository) {
        userEntity.setUuid(UUID.randomUUID());
        List<UserRoleEntity> userRoles = new ArrayList<>();
        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);
        userRoles.add(userRole);
        userEntity.setRoles(userRoles);
    }

}
