package com.web.curse;

import com.web.curse.dtos.save.*;
import com.web.curse.entities.Client;
import com.web.curse.entities.Role;
import com.web.curse.entities.enums.ClientRoles;
import com.web.curse.entities.enums.Meter;
import com.web.curse.repositories.interfaces.ClientRepository;
import com.web.curse.repositories.interfaces.ClientRoleRepository;
import com.web.curse.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private ClientDomainService clientDomainService;

    @Autowired
    private LandDomainService landDomainService;

    @Autowired
    private TargetFeeDomainService targetFeeDomainService;

    @Autowired
    private TargetFeePaymentDomainService targetFeePaymentDomainService;

    @Autowired
    private MembershipFeeDomainService membershipFeeDomainService;

    @Autowired
    TariffDomainService tariffDomainService;

    @Autowired
    TariffPaymentDomainService tariffPaymentDomainService;

    @Autowired
    ClientRoleRepository clientRoleRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... strings) {
        if(clientRoleRepository.findAll().size()==0){
            clientRoleRepository.save(new Role(ClientRoles.ADMIN));
            clientRoleRepository.save(new Role(ClientRoles.USER));
            clientRoleRepository.save(new Role(ClientRoles.METER));
        }
        clientRepository.save(new Client("a",passwordEncoder.encode("a"),"Admin","A","",List.of(clientRoleRepository.findByName(ClientRoles.ADMIN))));


        landDomainService.save(new LandSaveDto("238",6,Meter.SINGLE));
        landDomainService.save(new LandSaveDto("239",8,Meter.SINGLE));
        landDomainService.save(new LandSaveDto("240-а",6,Meter.DOUBLE));
        landDomainService.save(new LandSaveDto("240-б",6,Meter.SINGLE));
        clientDomainService.register(new ClientSaveDto("abob","pass1","Bob","Bobian","", List.of(1L,3L)));
        clientDomainService.register(new ClientSaveDto("1111","1111","Boba","Bobian","", List.of(2L)));

        targetFeeDomainService.save(new TargetFeeSaveDto("Дорога",10000, new Date()));
        membershipFeeDomainService.save(new MembershipFeeSaveDto(10000));
        tariffDomainService.save(new TariffSaveDto(1.2,1.3,2.3,3.4));
        tariffPaymentDomainService.saveMeterReadings(List.of(new TariffPaymentSaveDto(1,2,3,4,2)));
    }


}