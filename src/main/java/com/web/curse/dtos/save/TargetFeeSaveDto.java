package com.web.curse.dtos.save;

import java.util.Date;

public class TargetFeeSaveDto {
    public String targetName;
    public double contributionAmount;
    public Date endLocalDate;

    public TargetFeeSaveDto(String targetName, double contributionAmount, Date endLocalDate) {
        this.targetName = targetName;
        this.contributionAmount = contributionAmount;
        this.endLocalDate = endLocalDate;
    }
}
