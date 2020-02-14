package de.dlh.lhind.pharma.repository;

import de.dlh.lhind.pharma.models.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduktRepository extends JpaRepository<Produkt, Long> {


    @Query(value = "SELECT * FROM products WHERE to_date IS NULL", nativeQuery = true)
    public List<Produkt> findAll();


}
