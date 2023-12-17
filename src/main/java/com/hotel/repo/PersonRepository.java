package com.hotel.repo;


import com.hotel.entity.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Override
    @Query("FROM Person c JOIN FETCH c.address")
    List<Person> findAll();
}
