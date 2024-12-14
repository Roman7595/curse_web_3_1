package com.web.curse.dtos.LandUpdateDto;

import com.web.curse.entities.enums.Meter;

public class LandUpdateDto {
    public long id;
    public int sizeInArs;
    public Meter electricMeter;
    public long clientId;

    public LandUpdateDto(long id, int sizeInArs, Meter electricMeter, long clientId) {
        this.id = id;
        this.sizeInArs = sizeInArs;
        this.electricMeter = electricMeter;
        this.clientId = clientId;
    }
}
