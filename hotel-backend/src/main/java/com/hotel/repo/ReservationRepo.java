package com.hotel.repo;

import com.hotel.entity.reservation.Reservation;
import com.hotel.entity.room.Room;
import com.hotel.entity.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {


    @Override
    @Query("FROM Reservation r JOIN FETCH r.person p JOIN FETCH r.room")
    List<Reservation> findAll();



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

    @Query("SELECT r from Room r "+
            "WHERE r.id NOT IN " +
            "(SELECT r.room.id FROM Reservation r " +
            "WHERE r.start <= :end AND r.end >= :start)")
    List<Room> available(@Param("start") Date start,
                         @Param("end") Date end);

    void deleteByPerson(Person p);

    List<Reservation> findByPerson(Person p);
}
