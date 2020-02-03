package de.dlh.lhind.pharma.service;

import de.dlh.lhind.pharma.dto.UserDTO;
import de.dlh.lhind.pharma.models.Produkt;
import de.dlh.lhind.pharma.models.User;
import de.dlh.lhind.pharma.repository.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduktService {
    @Autowired
    private ProduktRepository produktRepository;

    public List<Produkt> findAll(){

        return produktRepository.findAll();
    }
}
