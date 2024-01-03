package com.hotel.entity.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.entity.room.Room;
import com.hotel.entity.user.Person;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Reservation {
    public Reservation(){
    }
    public Reservation(Person person, Room room, Date start, Date end) {
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
    private Person person;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @Column(name = "starts_at")
    private Date start;

    @Column(name = "ends_at")
    private Date end;

    @Column(name = "created_at")
    private Date created = new Date();

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
