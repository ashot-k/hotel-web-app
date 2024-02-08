package com.hotel.repo;

import com.hotel.entity.room.Room;
import com.hotel.entity.room.RoomType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Override
    Page<Room> findAll(Pageable pageable);

    @Override
    void deleteById(Long id);
    @Query("FROM Room r  " +
            "WHERE r.description like :term " +
            "OR r.name like :term " +
            "OR r.size like :term " +
            "ORDER BY r.id ")
    Page<Room> findByTerm(@Param("term") String term, Pageable pageable);

    @Query("FROM Room r " +
            "WHERE r.roomType = :term " +
            "ORDER BY r.id ")
    Page<Room> findByRoomType(@Param("term") RoomType term, Pageable pageable);

    Optional<Room> findByName(String name);
}
