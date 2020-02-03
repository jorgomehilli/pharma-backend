package de.dlh.lhind.pharma.repository;

import de.dlh.lhind.pharma.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
