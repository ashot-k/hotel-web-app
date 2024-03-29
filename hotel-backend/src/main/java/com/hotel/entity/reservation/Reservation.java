package com.hotel.entity.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.entity.room.Room;
import com.hotel.entity.user.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Reservation {
    public Reservation() {
    }

    public Reservation(Person person, Room room, LocalDate start, LocalDate end) {
        this.person = person;
        this.room = room;
        this.start = start;
        this.end = end;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client")
    @NotNull(message = "Please enter personId")
    @JsonIgnore
    private Person person;

    @ManyToOne
    @JoinColumn(name = "room")
    @NotNull(message = "Please enter roomId")
    @JsonIgnore
    private Room room;

    @Transient
    private Long personId;
    @Transient
    private Long roomId;

    @Column(name = "starts_at")
    @NotNull(message = "Invalid Date for start")
    @FutureOrPresent(message = "Invalid Date for start")
    private LocalDate start;

    @Column(name = "ends_at")
    @NotNull(message = "Invalid Date for end")
    @Future(message = "Invalid Date for end")
    private LocalDate end;

    @Column(name = "created_at")
    private LocalDate created = LocalDate.now();

    public Long getPersonId() {
        if (person != null) {
            return person.getId();
        }
        return null;
    }
    public Long getRoomId(){
        if(room != null){
            return room.getId();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
