package de.dlh.lhind.pharma.repository;

import de.dlh.lhind.pharma.models.Cart_Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<Cart_Items,Long> {
}
