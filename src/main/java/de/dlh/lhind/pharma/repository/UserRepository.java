package de.dlh.lhind.pharma.repository;

import de.dlh.lhind.pharma.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users  WHERE email = :email AND to_date IS NULL",
            nativeQuery = true)
    User findByEmail(@Param("email") String email);


}
