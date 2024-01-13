package com.hotel.repo;


import com.hotel.entity.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Override
    @Query("FROM Person p JOIN FETCH p.address a")
    List<Person> findAll();

    Optional<Person> findByUsername(String username);
}
