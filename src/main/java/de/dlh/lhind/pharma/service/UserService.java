package de.dlh.lhind.pharma.service;


import de.dlh.lhind.pharma.dto.UserDTO;
import de.dlh.lhind.pharma.exception.CustomException;
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

    public User signup(UserDTO userDTO) {

        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        verifyRoles();
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(6L))));
        user.setCreatedAt(new Date());

        if (userRepository.findByEmail(userDTO.getEmail()) != null){
            throw new CustomException("Oops, user with this email already exists !");
        }

        return userRepository.save(user);
    }

    public User addUser(UserDTO userDTO){

        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        verifyRoles();

        if (userDTO.getRole().equals("ROLE_USER")){
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(6L))));

        } else{
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(2L))));
        }
        user.setCreatedAt(new Date());

        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElse(null);
        user.setToDate(new Date());
        userRepository.save(user);
    }

    public void updateUser(UserDTO userDTO, Long id){
        User user = userRepository.findById(id).orElse(null);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getRole().equals("ROLE_USER")){
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(6L))));

        } else{
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(2L))));
        }
        userRepository.save(user);

    }

    private void verifyRoles(){
        if(!roleRepository.findById(6L).isPresent()) {
            roleRepository.save(new Roles("ROLE_USER"));
        }
        if(!roleRepository.findById(2L).isPresent()) {
            roleRepository.save(new Roles("ROLE_ADMIN"));
        }
    }

   public Boolean isEmailPresent(String email){
        User user = userRepository.findByEmail(email);
        if (user == null){
            return false;
        } else
return true;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
