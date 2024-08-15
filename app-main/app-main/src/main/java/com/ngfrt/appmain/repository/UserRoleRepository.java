package com.ngfrt.appmain.repository;

import com.ngfrt.appmain.model.entity.UserRoleEntity;
import com.ngfrt.appmain.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRole(UserRoleEnum roleEnum);
}
