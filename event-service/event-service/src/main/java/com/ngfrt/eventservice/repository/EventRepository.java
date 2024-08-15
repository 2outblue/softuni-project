package com.ngfrt.eventservice.repository;

import com.ngfrt.eventservice.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT e FROM Event e WHERE YEAR(e.date) = :year AND MONTH(e.date) = :month")
    List<Event> findAllByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT e FROM Event e WHERE MONTH(e.date) = :month AND DAY(e.date) = :day")
    List<Event> findAllByMonthAndDay(@Param("month") int month, @Param("day") int day);
}
