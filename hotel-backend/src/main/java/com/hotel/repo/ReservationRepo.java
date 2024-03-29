package com.hotel.repo;

import com.hotel.dto.ReservationDTO;
import com.hotel.entity.reservation.Reservation;
import com.hotel.entity.room.Room;
import com.hotel.entity.user.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository@Transactional
public interface ReservationRepo extends JpaRepository<Reservation, Long> {


    @Override
    @Query("FROM Reservation r JOIN FETCH r.person p JOIN FETCH r.room")
    List<Reservation> findAll();



    @Query("SELECT COUNT(r) > 0 FROM Reservation r " +
            "WHERE r.room.id = :roomId " +
            "AND r.id != :reservationId " +
            "AND r.start <= :end " +
            "AND r.end >= :start")
    boolean isRoomReserved(@Param("reservationId") Long reservationId, @Param("roomId") Long roomId, @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT r from Room r "+
            "WHERE r.id NOT IN " +
            "(SELECT res.room.id FROM Reservation res " +
            "WHERE res.start <= :end AND res.end >= :start)")
    List<Room> available(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("FROM Reservation r JOIN FETCH r.person p " +
            "WHERE p.username like :term " +
            "ORDER BY r.id ")
    Page<Reservation> findByTerm(@Param("term") String term, Pageable pageable);

    void deleteByPerson(Person p);

    List<Reservation> findByPerson(Person p);

    //Page<Reservation> findAll(Pageable pageable);
}
