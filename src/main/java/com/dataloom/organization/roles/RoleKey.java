package com.dataloom.organization.roles;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.UUID;

public class RoleKey {

    private final List<UUID> aclKey;
    private final UUID       organizationId;
    private final UUID       roleId;

    public RoleKey( UUID organizationId, UUID roleId ) {

        this.organizationId = organizationId;
        this.roleId = roleId;
        this.aclKey = ImmutableList.of( organizationId, roleId );
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
        return "RoleKey { organizationId=" + organizationId + ", roleId=" + roleId + " }";
    }
}
