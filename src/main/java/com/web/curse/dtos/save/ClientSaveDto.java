package com.web.curse.dtos.save;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;

public class ClientSaveDto {
    @NotBlank(message = "Login cant be empty")
    public String login;
    @NotBlank(message = "Password cant be empty")
    public String password;
    @NotBlank(message = "Name cant be empty")
    public String name;
    @NotBlank(message = "Middle name cant be empty")
    public String middleName;
    public String lastName;
    @NotBlank(message = "Please choose your lands")
    public List<Long> landIds;

    public ClientSaveDto(String login, String password, String name, String middleName, String lastName, List<Long> landIds) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.landIds = landIds;
    }
}
