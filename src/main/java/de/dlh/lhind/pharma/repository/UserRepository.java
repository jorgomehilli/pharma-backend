package de.dlh.lhind.pharma.repository;

import de.dlh.lhind.pharma.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users  WHERE email = :email AND to_date IS NULL",
            nativeQuery = true)
//    @Query(value = "select u from User u  where u.email = :email and u.toDate is null")
    User findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM users WHERE to_date IS NULL", nativeQuery = true)
    List<User> findAll();


}
