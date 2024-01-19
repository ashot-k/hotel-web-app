package com.hotel.repo;


import com.hotel.entity.user.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Override
    @Query("FROM Person p JOIN FETCH p.address a")
    List<Person> findAll();
    @Override
    @Query("FROM Person p JOIN FETCH p.address a ORDER BY p.id")
    Page<Person> findAll(Pageable pageable);


    @Query("FROM Person p JOIN FETCH p.address a " +
            "WHERE p.username like :term " +
            "OR p.address.country like :term " +
            "OR p.address.email like :term " +
            "OR p.address.phoneNumber like :term " +
            "OR p.address.postalCode like :term " +
            " order by p.id ")
    List<Person> findByTerm(@Param("term") String term);

    Optional<Person> findByUsername(String username);


}
