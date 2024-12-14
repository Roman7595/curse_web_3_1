package com.web.curse.services.impl;

import com.web.curse.dtos.account.*;
import com.web.curse.dtos.out.*;
import com.web.curse.dtos.save.ClientSaveDto;
import com.web.curse.entities.*;
import com.web.curse.entities.enums.ClientRoles;
import com.web.curse.repositories.interfaces.*;
import com.web.curse.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class ClientDomainServiceImpl implements ClientDomainService {

    private ClientRoleRepository clientRoleRepository;


    private PasswordEncoder passwordEncoder;
    private ClientRepository clientRepository;

    private LandRepository landRepository;

    private LandDomainService landDomainService;
    private MembershipFeeDomainService membershipFeeDomainService;
    private TariffPaymentDomainService tariffPaymentDomainService;
    private TargetFeePaymentDomainService targetFeePaymentDomainService;
    private TargetFeeDomainService targetFeeDomainService;
    private TariffDomainService tariffDomainService;
    private MembershipFeePaymentDomainService membershipFeePaymentDomainService;
    @Autowired
    public ClientDomainServiceImpl(ClientRepository clientRepository, LandRepository landRepository, LandDomainService landDomainService, MembershipFeeDomainService membershipFeeDomainService, TariffPaymentDomainService tariffPaymentDomainService, TargetFeePaymentDomainService targetFeePaymentDomainService, TargetFeeDomainService targetFeeDomainService, TariffDomainService tariffDomainService,
                                   MembershipFeePaymentDomainService membershipFeePaymentDomainService, PasswordEncoder passwordEncoder, ClientRoleRepository clientRoleRepository) {
        this.clientRepository = clientRepository;
        this.landRepository = landRepository;
        this.landDomainService = landDomainService;
        this.membershipFeeDomainService = membershipFeeDomainService;
        this.tariffPaymentDomainService = tariffPaymentDomainService;
        this.targetFeePaymentDomainService = targetFeePaymentDomainService;
        this.targetFeeDomainService = targetFeeDomainService;
        this.tariffDomainService = tariffDomainService;
        this.membershipFeePaymentDomainService = membershipFeePaymentDomainService;
        this.passwordEncoder = passwordEncoder;
        this.clientRoleRepository = clientRoleRepository;
    }

    @Caching(evict = {
            @CacheEvict(cacheNames ="clients", allEntries = true),
            @CacheEvict(cacheNames = "lands", allEntries = true)
    })
    public ClientOutputDto register(ClientSaveDto clientToSave) {
        if(clientRepository.findByLogin(clientToSave.login)!=null){
            throw new RuntimeException();//error: login taken
        }

        Role role = clientRoleRepository.findByName(ClientRoles.USER);

        if(role==null){
            throw new RuntimeException("");//no user role
        }

        Client client = new Client(clientToSave.login,
                passwordEncoder.encode(clientToSave.password),
                clientToSave.name,
                clientToSave.middleName,
                clientToSave.lastName,
                List.of(role));

        if(clientRepository.save(client) == null){
            throw new RuntimeException();//error: cant create user
        }

        for (long id:clientToSave.landIds) {
            Optional<Land> land = landRepository.findById(id);
            if(land.isPresent() && land.get().getClient()==null){//error: other already owned
                land.get().setClient(client);
                landRepository.update(land.get());
            }

        }

        if(landRepository.findByClient(client) == null){
            throw new RuntimeException(); //non of lands found
        }

        return new ClientOutputDto(client.getId(),client.getLogin(),client.getName(),client.getMiddleName(),client.getLastName());
    }

    public List<ClientWithDebtOutputDto> getDeptLeaderboard() {
        List<Client> clients = clientRepository.findAll();
        List<ClientWithDebtOutputDto> result = new ArrayList<>();
        for (Client client:clients) {
            double membershipDept = 0;
            double targetDept = 0;
            double tariffDept = 0;
            for (Land l:client.getLands()) {
                for (MembershipFeePayment membershipFeePayment:l.getMembershipFeePayments()) {
                    if (membershipFeePayment.getPaymentLocalDate()==null){
                        membershipDept+=membershipFeePayment.getFeeSum();
                    }
                }
                for (TargetFeePayment targetFeePayment:l.getTargetFeePayments()) {
                    if (targetFeePayment.getPaymentLocalDate()==null){
                        targetDept+=targetFeePayment.getFeeSum();
                    }
                }
                for (TariffPayment tariffPayment:l.getTariffPayments()) {
                    if (tariffPayment.getPaymentLocalDate()==null){
                        Tariff tariff = tariffPayment.getTariff();
                        switch (l.getElectricMeter()){
                            case SINGLE -> tariffDept+= tariffPayment.getSingleElectricalUsage() * tariff.getSingleElectricalTariff();
                            case DOUBLE -> {
                                double daySum = tariffPayment.getDoubleElectricalDayUsage() * tariff.getDoubleElectricalTariffDay();
                                double nightSum = tariffPayment.getDoubleElectricalNightUsage() * tariff.getDoubleElectricalTariffNight();
                                tariffDept += daySum + nightSum;
                            }
                        }
                        tariffDept += tariffPayment.getWaterUsage() * tariff.getWaterTariff();
                    }
                }
            }
            double totalDept = membershipDept + targetDept + tariffDept;
            ClientWithDebtOutputDto clientWithDept = new ClientWithDebtOutputDto(client.getId(),client.getLogin(),client.getName(),client.getMiddleName(),client.getLastName(),tariffDept,targetDept,membershipDept,totalDept);
            result.add(clientWithDept);
        }

        return result.stream().sorted((client1, client2) -> (int) (client2.totalDept - client1.totalDept)).limit(5).toList();
    }

    public ClientOutputDto findById(long id){
        Optional<Client> c = clientRepository.findById(id);
        if(c.isEmpty()){
            throw new RuntimeException();//client not found
        }

        return new ClientOutputDto(c.get().getId(),c.get().getLogin(),c.get().getName(),c.get().getMiddleName(),c.get().getLastName());
    }

    @Cacheable("clients")
    public List<ClientOutputDto> getAll(){
        List<ClientOutputDto> result = new ArrayList<>();
        for (Client c:clientRepository.findAll()) {
            result.add(new ClientOutputDto(c.getId(),c.getLogin(),c.getName(),c.getMiddleName(),c.getLastName()));
        }
        return result;
    }


    public FullClientOutputDto createFullClient(long id){
        ClientOutputDto client = this.findById(id);
        List<FullLandOutputDto> lands = new ArrayList<>();
        for (LandOutputDto l:landDomainService.allClientLands(id)) {
            List<FullTariffPaymentOutputDto> tariffOutputDtos = new ArrayList<>();
            List<FullTargetFeePaymentOutputDto> targetFeeOutputDtos = new ArrayList<>();
            List<FullMembershipFeePaymentOutputDto> membershipFeeOutputDtos = new ArrayList<>();

            for (TariffPaymentOutputDto tp:tariffPaymentDomainService.findByLand(l.id)) {
                TariffOutputDto tariffOutputDto = tariffDomainService.findByPayment(tp.id());
                tariffOutputDtos.add(new FullTariffPaymentOutputDto(tp,tariffOutputDto));
            }

            for (TargetFeePaymentOutputDto tp:targetFeePaymentDomainService.findByLand(l.id)) {
                TargetFeeOutputDto targetFeeOutputDto = targetFeeDomainService.findByPayment(tp.id);
                targetFeeOutputDtos.add(new FullTargetFeePaymentOutputDto(tp, targetFeeOutputDto));
            }

            for (MembershipFeePaymentOutputDto mp:membershipFeePaymentDomainService.findByLand(l.id)) {
                MembershipFeeOutputDto membershipFeePayment = membershipFeeDomainService.findByPayment(mp.id);
                membershipFeeOutputDtos.add(new FullMembershipFeePaymentOutputDto(mp, membershipFeePayment));
            }

            lands.add(new FullLandOutputDto(l, tariffOutputDtos, targetFeeOutputDtos, membershipFeeOutputDtos));
        }
        return new FullClientOutputDto(client, lands);
    }


    public ClientOutputDto findByLogin(String login){
        Client c = clientRepository.findByLogin(login);
        if (c == null){
            throw new RuntimeException("Client Not found");//error:
        }
        return new ClientOutputDto(c.getId(),c.getLogin(),c.getName(),c.getMiddleName(),c.getLastName());
    }
}
