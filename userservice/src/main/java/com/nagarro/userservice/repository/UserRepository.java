package com.nagarro.userservice.repository;

import com.nagarro.userservice.model.Token;
import com.nagarro.userservice.model.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@NonNullApi
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select e from User e where e.deleted=false and e.id =:id")
    Optional<User> findById(Long id);

    @Query("select e from User e where e.deleted=false")
    List<User> findAll();

    @Modifying
    @Query("update User e set e.deleted = true where e.id = :id")
     void deleteById(Long id);

    User save(User user);

    Token save(Token token);


    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);







}
