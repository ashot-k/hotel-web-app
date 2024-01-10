package com.hotel.repo;

import com.hotel.entity.reservation.Reservation;
import com.hotel.entity.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    @Query("SELECT COUNT(r) > 0 FROM Reservation r " +
            "WHERE r.room.id = :roomId " +
            "AND r.id != :reservationId " +
            "AND r.start <= :end " +
            "AND r.end >= :start")
    boolean isRoomReserved(@Param("reservationId") Long reservationId,
                           @Param("roomId") Long roomId,
                           @Param("start") Date start,
                           @Param("end") Date end

    );

    void deleteByPerson(Person p);

    List<Reservation> findByPerson(Person p);
}
