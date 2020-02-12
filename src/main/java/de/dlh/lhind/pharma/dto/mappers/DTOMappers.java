package de.dlh.lhind.pharma.dto.mappers;

import de.dlh.lhind.pharma.dto.CartItemDTO;
import de.dlh.lhind.pharma.models.Cart_Items;
import org.springframework.stereotype.Service;

@Service
public class DTOMappers {

    public CartItemDTO cartItemDTOMapper(Cart_Items cart_items) {
        return new CartItemDTO(cart_items.getProduct().getName(),
                                cart_items.getProduct().getPrice(),
                                cart_items.getQuantity());
    }
}
