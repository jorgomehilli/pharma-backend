package de.dlh.lhind.pharma.repository;

import de.dlh.lhind.pharma.models.Cart_Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<Cart_Items,Long> {


//    @Query(value = "SELECT p.name, p.price, c.quantity FROM cart_items c INNER JOIN products p ON c.product_id = p.id WHERE c.user_id=:userId ",
//            nativeQuery = true)
//    List<CartItemDTO> getCurrentUserItems(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM cart_items WHERE user_id = :userId", nativeQuery = true)
    public List<Cart_Items> findByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "DELETE FROM cart_items WHERE id = :id", nativeQuery = true)
    public void deleteItem(@Param("id") Long id);

    @Query(value = "SELECT * FROM cart_items WHERE user_id =:userId AND product_id =:productId", nativeQuery = true)
    public Cart_Items isPresent(@Param("userId") Long userId,
                                @Param("productId") Long productId);
    @Modifying
    @Query(value ="DELETE FROM cart_items WHERE user_id=:userId", nativeQuery = true)
    public void purchaseCartItems(@Param("userId") Long userId);

}
