package com.web.curse.repositories.interfaces;


import com.web.curse.entities.Role;
import com.web.curse.entities.enums.ClientRoles;

import java.util.List;

public interface ClientRoleRepository {
    Role save(Role role);
    List<Role> findAll();
    Role findByName(ClientRoles name);
}
