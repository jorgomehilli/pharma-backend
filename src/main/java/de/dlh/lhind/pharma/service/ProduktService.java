package de.dlh.lhind.pharma.service;

import de.dlh.lhind.pharma.models.Cart_Items;
import de.dlh.lhind.pharma.models.Produkt;
import de.dlh.lhind.pharma.models.User;
import de.dlh.lhind.pharma.repository.CartItemsRepository;
import de.dlh.lhind.pharma.repository.ProduktRepository;
import de.dlh.lhind.pharma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class ProduktService {
    @Autowired
    private ProduktRepository produktRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private CartItemsRepository cartItemsRepository;



    public List<Produkt> findAll(){

        return produktRepository.findAll();
    }

    public Cart_Items addToCart(Long productId){

        User user = myUserDetailsService.getCurrentUser();
        Cart_Items cart_item = new Cart_Items();
        cart_item.setUser(user);
        Produkt product = produktRepository.getOne(productId);
        cart_item.setProduct(product);
        cart_item.setQuantity(1);
       return cartItemsRepository.save(cart_item);

    }

    public List<Object> getCurrentUserItems(){
        return cartItemsRepository.getCurrentUserItems(myUserDetailsService.getCurrentUser().getUserId());
    }
}
