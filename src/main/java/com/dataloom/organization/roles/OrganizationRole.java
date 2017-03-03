package com.dataloom.organization.roles;

import java.util.UUID;

import com.dataloom.authorization.Principal;
import com.dataloom.authorization.PrincipalType;
import com.dataloom.authorization.securable.AbstractSecurableObject;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Optional;

public class OrganizationRole extends AbstractSecurableObject {
    private UUID      organizationId;
    private Principal principal;

    public OrganizationRole( Optional<UUID> id, UUID organizationId, String title, Optional<String> description ) {
        super( id, title, description );
        this.organizationId = organizationId;
        this.principal = new Principal( PrincipalType.ROLE, organizationId + "|" + title );
    }

    public UUID getOrganizationId() {
        return organizationId;
    }

    @JsonIgnore
    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public SecurableObjectType getCategory() {
        return SecurableObjectType.OrganizationRole;
    }

}
