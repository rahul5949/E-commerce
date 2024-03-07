package com.nagarro.userservice.repository;

import com.nagarro.userservice.dto.UserDetailsDto;
import com.nagarro.userservice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDetails, Long> {


    @Query("select e from UserDetails e where e.isDeleted=false and e.id =:id")
    Optional<UserDetails> findById(Long id);

    @Query("select e from UserDetails e where e.isDeleted=false")
    List<UserDetails> findAll();

    @Modifying
    @Query("update UserDetails e set e.isDeleted = true where e.id = :id")
     void deleteById(Long id);

    UserDetails save(UserDetails userDetails);



}
