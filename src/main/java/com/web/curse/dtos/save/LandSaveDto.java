package com.web.curse.dtos.save;

import com.web.curse.entities.enums.Meter;

public class LandSaveDto {
    public String number;
    public int sizeInArs;
    public Meter electricMeter;

    public LandSaveDto(String number, int sizeInArs, Meter electricMeter) {
        this.number = number;
        this.sizeInArs = sizeInArs;
        this.electricMeter = electricMeter;
    }
}
