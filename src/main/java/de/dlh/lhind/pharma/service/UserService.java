package de.dlh.lhind.pharma.service;


import de.dlh.lhind.pharma.dto.UserDTO;
import de.dlh.lhind.pharma.models.Roles;
import de.dlh.lhind.pharma.models.User;
import de.dlh.lhind.pharma.repository.RoleRepository;
import de.dlh.lhind.pharma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User signup(UserDTO userDTO){

        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        verifyRoles();
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(6L))));
        user.setCreatedAt(new Date());

        return userRepository.save(user);
    }

    private void verifyRoles(){
        if(!roleRepository.findById(6L).isPresent()) {
            roleRepository.save(new Roles("ROLE_USER"));
        }
        if(!roleRepository.findById(2L).isPresent()) {
            roleRepository.save(new Roles("ROLE_ADMIN"));
        }
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
