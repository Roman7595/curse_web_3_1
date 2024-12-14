package com.web.curse.services.impl;

import com.web.curse.dtos.out.LandOutputDto;
import com.web.curse.dtos.save.LandSaveDto;
import com.web.curse.entities.*;
import com.web.curse.repositories.interfaces.ClientRepository;
import com.web.curse.repositories.interfaces.LandRepository;
import com.web.curse.services.LandDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class LandDomainServiceImpl implements LandDomainService {
    @Autowired
    public LandDomainServiceImpl(ClientRepository clientRepository, LandRepository landRepository) {
        this.clientRepository = clientRepository;
        this.landRepository = landRepository;
    }

    ClientRepository clientRepository;
    LandRepository landRepository;

    @CacheEvict(cacheNames = "lands", allEntries = true)
    public LandOutputDto save(LandSaveDto landToSave) {
        Land land = new Land(landToSave.number,landToSave.sizeInArs,landToSave.electricMeter);


        if(landRepository.findByNumber(land.getNumber())!=null){
            throw new RuntimeException();//error: land already exists
        }

        if(landRepository.save(land) == null){
            throw new RuntimeException();//cant create user
        }
        return new LandOutputDto(land.getId(),land.getNumber(),land.getSizeInArs(),land.getElectricMeter());
    }


    public LandOutputDto findById(long id){
        Optional<Land> land = landRepository.findById(id);
        if(land.isEmpty()){
            throw new RuntimeException();//client not found
        }
        return new LandOutputDto(land.get().getId(), land.get().getNumber(),land.get().getSizeInArs(),land.get().getElectricMeter());
    }
    @Cacheable("lands")
    public List<LandOutputDto> getAll(){
        List<LandOutputDto> result = new ArrayList<>();
        for (Land l:landRepository.findAll()) {
            result.add(new LandOutputDto(l.getId(), l.getNumber(),l.getSizeInArs(),l.getElectricMeter()));
        }
        return result;
    }
    public List<LandOutputDto> getOnlyFree(){
        List<LandOutputDto> result = new ArrayList<>();
        for (Land l:landRepository.findAll()) {
            if(l.getClient()==null){
                result.add(new LandOutputDto(l.getId(), l.getNumber(),l.getSizeInArs(),l.getElectricMeter()));
            }
        }
        return result;
    }

    public List<LandOutputDto> allClientLands(long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isEmpty()){
            throw new RuntimeException(); // client not found
        }

        List<LandOutputDto> lands = new ArrayList<>();

        for (Land l:landRepository.findByClient(client.get())) {
            lands.add(new LandOutputDto(l.getId(), l.getNumber(), l.getSizeInArs(), l.getElectricMeter()));
        }

        return lands;
    }

}
