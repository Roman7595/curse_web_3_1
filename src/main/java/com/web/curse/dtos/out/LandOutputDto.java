package com.web.curse.dtos.out;

import com.web.curse.entities.enums.Meter;

public class LandOutputDto {
    public long id;
    public String number;
    public int sizeInArs;
    public Meter electricMeter;

    public LandOutputDto(long id, String number, int sizeInArs, Meter electricMeter) {
        this.id = id;
        this.number = number;
        this.sizeInArs = sizeInArs;
        this.electricMeter = electricMeter;

    }

    protected LandOutputDto() {
    }
}
