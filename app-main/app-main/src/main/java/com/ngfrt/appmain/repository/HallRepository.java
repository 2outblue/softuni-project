package com.ngfrt.appmain.repository;

import com.ngfrt.appmain.model.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    Hall getByNumber(Integer number);
    Hall getByUuid(UUID uuid);
    Hall getByName(String name);
}
