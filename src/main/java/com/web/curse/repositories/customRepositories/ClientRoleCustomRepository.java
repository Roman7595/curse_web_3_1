package com.web.curse.repositories.customRepositories;

import com.web.curse.entities.Role;
import com.web.curse.entities.enums.ClientRoles;
import org.springframework.data.repository.Repository;



public interface ClientRoleCustomRepository extends Repository<Role, Long> {
    Role findByName(ClientRoles role);
}
