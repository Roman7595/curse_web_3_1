package com.web.curse.dtos.out;

import java.util.Date;



    public record TariffOutputDto(long id,
                                  double waterTariff,
                                  double singleElectricalTariff,
                                  double doubleElectricalTariffDay,
                                  double doubleElectricalTariffNight,
                                  Date startLocalDate) {
    }

