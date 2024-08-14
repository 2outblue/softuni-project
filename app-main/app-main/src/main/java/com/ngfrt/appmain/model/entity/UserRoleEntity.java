package com.ngfrt.appmain.model.entity;


import com.ngfrt.appmain.model.entity.enums.UserRoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity extends BaseEntity {

    @Enumerated
    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
