package de.dlh.lhind.pharma.controller;

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

}
