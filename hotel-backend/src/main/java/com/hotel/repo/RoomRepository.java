package com.hotel.repo;

import com.hotel.entity.room.Room;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Override
    Page<Room> findAll(Pageable pageable);

    @Override
    void deleteById(Long id);
}
