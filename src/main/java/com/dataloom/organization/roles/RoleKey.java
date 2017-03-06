package com.dataloom.organization.roles;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RoleKey {
    private UUID       organizationId;
    private UUID       roleId;

    private List<UUID> aclKey;

    public RoleKey( UUID organizationId, UUID roleId ) {
        this.organizationId = organizationId;
        this.roleId = roleId;
        aclKey = Arrays.asList( organizationId, roleId );
    }

    public UUID getOrganizationId() {
        return organizationId;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public List<UUID> getAclKey() {
        return aclKey;
    }

    @Override
    public String toString() {
        return organizationId + "|" + roleId;
    }
}
