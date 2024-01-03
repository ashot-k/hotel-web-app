package com.hotel.repo;

import com.hotel.entity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
   // List<Reservation>  findByStartBetween(Date start, Date start2);

    @Query("SELECT COUNT(r) > 0 FROM Reservation r " +
            "WHERE r.room.id = :roomId " +
            "AND r.start <= :end " +
            "AND r.end >= :start")
    boolean isRoomBooked(@Param("roomId") Long roomId,
                         @Param("start") Date start,
                         @Param("end") Date end
    );
}
