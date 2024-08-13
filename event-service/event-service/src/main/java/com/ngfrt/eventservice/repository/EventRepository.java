package com.ngfrt.eventservice.repository;

import com.ngfrt.eventservice.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    List<Event> findAllByUserId(UUID uuid);
    List<Event> findAllByHallId(UUID uuid);

    List<Event> findAllByFeatured(boolean featured);
}
