package com.web.curse.dtos.out;

public class ClientWithDebtOutputDto {
    public long id;
    public String login;

    public String name;
    public String middleName;
    public String lastName;

    public double tariffDept;

    public double targetDept;
    public double membershipDept;
    public double totalDept;

    public ClientWithDebtOutputDto(long id, String login, String name, String middleName, String lastName, double tariffDept, double targetDept, double membershipDept, double totalDept) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.tariffDept = tariffDept;
        this.targetDept = targetDept;
        this.membershipDept = membershipDept;
        this.totalDept = totalDept;
    }
}
