package de.dlh.lhind.pharma.controller;


import de.dlh.lhind.pharma.dto.UserDTO;
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
    public User addUser(@RequestBody UserDTO userDTO){
    return userService.signup(userDTO);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
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

}
