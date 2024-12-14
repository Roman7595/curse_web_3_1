package com.web.curse.entities;

import com.web.curse.entities.enums.ClientRoles;
import jakarta.persistence.*;
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    private ClientRoles name;

    public Role(ClientRoles name) {
        this.name = name;
    }

    protected Role() {

    }

    @Column(unique = true)
    public ClientRoles getName() {
        return name;
    }

    public void setName(ClientRoles name) {
        this.name = name;
    }
}