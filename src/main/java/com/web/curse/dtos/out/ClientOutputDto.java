package com.web.curse.dtos.out;



public class ClientOutputDto {
    public long id;
    public String login;

    public String name;
    public String middleName;
    public String lastName;

    public ClientOutputDto(long id, String login, String name, String middleName, String lastName) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    protected ClientOutputDto() {
    }
}
