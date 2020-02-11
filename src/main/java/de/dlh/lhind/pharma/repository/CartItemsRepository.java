package de.dlh.lhind.pharma.repository;

import de.dlh.lhind.pharma.models.Cart_Items;
import de.dlh.lhind.pharma.models.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<Cart_Items,Long> {

    @Query(value = "SELECT p.name, p.img, p.price, c.quantity FROM cart_items c INNER JOIN products p ON c.product_id = p.id WHERE c.user_id=:userId ",
            nativeQuery = true)
    List<Object> getCurrentUserItems(@Param("userId") Long userId);
}
