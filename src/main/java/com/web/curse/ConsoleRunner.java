package com.web.curse;

import com.web.curse.entities.Client;
import com.web.curse.entities.Land;
import com.web.curse.enums.Meter;
import com.web.curse.repositories.interfaces.ClientRepository;
import com.web.curse.repositories.interfaces.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LandRepository landRepository;

    @Override
    public void run(String... strings) {
        Client client = new Client("aaa","11","РР","sdad","saca");
        clientRepository.save(client);
        Land land = new Land("111",8, Meter.SINGLE);
        landRepository.save(land);
        Optional<Client> clientOpt = clientRepository.findById(1);
        clientOpt.ifPresent(land::setClient);
        landRepository.update(land);

        Client client1 = clientRepository.getWhereLand(land);
        System.out.println(client1.getName());
    }


}