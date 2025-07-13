package com.nerdyGeek.smat.dto;

import com.nerdyGeek.smat.entities.PermissionsEntity;
import com.nerdyGeek.smat.entities.RolesEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class RolesDTO {

    public Long id;
    public String name;
    public Set<PermissionsEntity> permissions;

    RolesDTO(RolesEntity rolesEntity) {
        this.id = rolesEntity.getId();
        this.name = rolesEntity.getName();
        this.permissions = rolesEntity.getPermissions();
    }
}
