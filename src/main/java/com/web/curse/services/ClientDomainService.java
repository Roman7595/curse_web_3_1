package com.web.curse.services;

import com.web.curse.dtos.account.FullClientOutputDto;
import com.web.curse.dtos.out.ClientOutputDto;
import com.web.curse.dtos.out.ClientWithDebtOutputDto;
import com.web.curse.dtos.save.ClientSaveDto;

import java.util.List;

public interface ClientDomainService {
    ClientOutputDto register(ClientSaveDto client);

    List<ClientWithDebtOutputDto> getDeptLeaderboard();
    ClientOutputDto findById(long id);
    List<ClientOutputDto> getAll();

    FullClientOutputDto createFullClient(long id);

    ClientOutputDto findByLogin(String login);
}
