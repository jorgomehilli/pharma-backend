package de.dlh.lhind.pharma.service;

import de.dlh.lhind.pharma.dto.CartItemDTO;
import de.dlh.lhind.pharma.dto.ProductDTO;
import de.dlh.lhind.pharma.dto.mappers.DTOMappers;
import de.dlh.lhind.pharma.models.Cart_Items;
import de.dlh.lhind.pharma.models.Produkt;
import de.dlh.lhind.pharma.models.User;
import de.dlh.lhind.pharma.repository.CartItemsRepository;
import de.dlh.lhind.pharma.repository.ProduktRepository;
import de.dlh.lhind.pharma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private DTOMappers dtoMappers;



    public List<Produkt> findAll(){

        return produktRepository.findAll();
    }

    public Cart_Items addToCart(Long productId) {

        Cart_Items existingCartItem = cartItemsRepository
                .isPresent(myUserDetailsService.getCurrentUser()
                        .getUserId(), productId);

        if (existingCartItem != null) {
            if(existingCartItem.getQuantity()<5){
            incrementQuantity(existingCartItem.getId());
            return existingCartItem;}
            else{return null;}
        } else {

            User user = myUserDetailsService.getCurrentUser();
            Cart_Items cart_item = new Cart_Items();
            cart_item.setUser(user);
            Produkt product = produktRepository.getOne(productId);
            cart_item.setProduct(product);
            cart_item.setQuantity(1);
            return cartItemsRepository.save(cart_item);

        }
    }

    //@Transactional
    public void deleteProduct(Long id){



      Produkt produkt = produktRepository.findById(id).orElse(null);
      produkt.setToDate(new Date());
      produktRepository.save(produkt);
    }

    public Produkt addProduct(ProductDTO productDTO){
        Produkt produkt = new Produkt();
        produkt.setName(productDTO.getName());
        produkt.setPrice((int)productDTO.getPrice());
        produkt.setImgPath(productDTO.getImgPath());
        produkt.setQuantity(40);
        System.out.println(produkt.getQuantity());
        produkt.setToDate(null);
        return produktRepository.save(produkt);
    }

    public List<CartItemDTO> getCurrentUserItems(){
        return cartItemsRepository.findByUserId(myUserDetailsService.getCurrentUser().getUserId()).stream()
                .map(ci -> dtoMappers.cartItemDTOMapper(ci))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCartItem (Long cartItemId){
        cartItemsRepository.deleteItem(cartItemId);
    }

    public void incrementQuantity(Long cartItemId){
        Cart_Items cart_item = cartItemsRepository.getOne(cartItemId);
        Integer newQuantity = cart_item.getQuantity() + 1;
        cart_item.setQuantity(newQuantity);
        cartItemsRepository.save(cart_item);
    }

    public void decrementQuantity(Long cartItemId){
        Cart_Items cart_item = cartItemsRepository.getOne(cartItemId);
        Integer newQuantity = cart_item.getQuantity() - 1;
        cart_item.setQuantity(newQuantity);
        cartItemsRepository.save(cart_item);
    }


}
