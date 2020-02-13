package de.dlh.lhind.pharma.controller;

import de.dlh.lhind.pharma.dto.CartItemDTO;
import de.dlh.lhind.pharma.models.Cart_Items;
import de.dlh.lhind.pharma.models.Produkt;
import de.dlh.lhind.pharma.service.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ProduktController {
    @Autowired
    private ProduktService produktService;

    @GetMapping("/products")
    public List<Produkt> getAllProducts(){
        return produktService.findAll();
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String Greeting(){
        return "Hello Boss";
    }


    @PostMapping("/cart/add")
    public Cart_Items addToCart(@RequestBody Long productId){
       return produktService.addToCart(productId);
    }

    @GetMapping("/users/cart")
    public List<CartItemDTO> getCartItems(){
        return produktService.getCurrentUserItems();
    }

    @RequestMapping(value = "/cart/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCartItem(@PathVariable Long id){
    produktService.deleteCartItem(id);
    }

    @GetMapping("/cart/increment/{id}")
    public void incrementQuantity(@PathVariable Long id){
    produktService.incrementQuantity(id);
    }

    @GetMapping("/cart/decrement/{id}")
    public void decrementQuantity(@PathVariable Long id){
        produktService.decrementQuantity(id);
    }

    }

