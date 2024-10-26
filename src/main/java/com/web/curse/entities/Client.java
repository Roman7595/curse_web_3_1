package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "clients")
public class Client extends BaseEntity{
    private String login;
    private String password;
    private String name;
    private String middleName;
    private String lastName;
    private Set<Land> lands;

    @Column(name = "login",unique = true, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "middle_name", nullable = false)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public Client(String login, String password, String name, String middleName, String lastName) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    public Set<Land> getLands() {
        return lands;
    }

    public void setLands(Set<Land> lands) {
        this.lands = lands;
    }

    protected Client() {
    }
}
