package com.web.curse.dtos.out;

import java.util.Date;




    public record TariffPaymentOutputDto(long id,
                                         double waterUsage,
                                         double singleElectricalUsage,
                                         double doubleElectricalDayUsage,
                                         double doubleElectricalNightUsage,
                                         double waterSum,
                                         double singleElectricalSum,
                                         double doubleElectricalDaySum,
                                         double doubleElectricalNightSum,
                                         Date paymentLocalDate,
                                         Date startLocalDate,
                                         Date endLocalDate) {
    }

