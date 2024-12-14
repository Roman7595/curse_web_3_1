package com.web.curse.services;

import com.web.curse.dtos.out.LandOutputDto;
import com.web.curse.dtos.save.LandSaveDto;

import java.util.List;

public interface LandDomainService {
    LandOutputDto save(LandSaveDto land);
    List<LandOutputDto> allClientLands(long clientId);

    LandOutputDto findById(long id);
    List<LandOutputDto> getAll();

    List<LandOutputDto> getOnlyFree();
}
