package com.web.curse.dtos.account;

import com.web.curse.dtos.out.ClientOutputDto;

import java.util.List;

public record FullClientOutputDto(
        ClientOutputDto clientOutputDto,
        List<FullLandOutputDto> lands
) {
}
