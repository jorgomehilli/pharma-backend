package de.dlh.lhind.pharma.controller;


import de.dlh.lhind.pharma.dto.UserDTO;
import de.dlh.lhind.pharma.exception.CustomException;
import de.dlh.lhind.pharma.models.AuthenticationRequest;
import de.dlh.lhind.pharma.models.AuthenticationResponse;
import de.dlh.lhind.pharma.models.User;
import de.dlh.lhind.pharma.service.MyUserDetailsService;
import de.dlh.lhind.pharma.service.UserService;
import de.dlh.lhind.pharma.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @PostMapping("/users/signup")
    public User addUser(@RequestBody UserDTO userDTO) {
    return userService.signup(userDTO);
    }

    @PostMapping("/users/add")
    public User addUserFromAdmin(@RequestBody UserDTO userDTO){
       return userService.addUser(userDTO);
    }

    @RequestMapping(value = "/users/update/{id}", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody UserDTO userDTO,
                              @PathVariable Long id){
            userService.updateUser(userDTO, id);
    }

    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id){
    userService.deleteUser(id);
    }



    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {

            throw new CustomException("Oops, wrong username or password !");

        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String firstName = userDetailsService.getCurrentUserName(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, firstName));

    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers (){
        return userService.getAllUsers();
    }

    @GetMapping("/users/email/{email}")
    public Boolean isEmailTaken(@PathVariable String email){
        System.out.println(email);
        return userService.isEmailPresent(email);
    }

}
