package de.dlh.lhind.pharma.controller;


import de.dlh.lhind.pharma.dto.UserDTO;
import de.dlh.lhind.pharma.models.User;
import de.dlh.lhind.pharma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users/signup")
    public User addUser(@RequestBody UserDTO userDTO){
    return userService.signup(userDTO);
    }
}
