package com.dataloom.organization.roles;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.dataloom.authorization.Principal;
import com.dataloom.authorization.PrincipalType;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.openlattice.authorization.SecurablePrincipal;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Role extends SecurablePrincipal {

    private static final Logger logger = LoggerFactory.getLogger( Role.class );

    private final UUID organizationId;

    @JsonCreator
    public Role(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.ORGANIZATION_ID ) UUID organizationId,
            @JsonProperty( SerializationConstants.PRINCIPAL ) Principal principal,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description ) {

        super( id, principal, title, description );
        checkArgument( principal.getType().equals( PrincipalType.ROLE ) );
        this.organizationId = checkNotNull( organizationId, "Organization id cannot be null." );
    }

    @JsonProperty( SerializationConstants.ORGANIZATION_ID )
    public UUID getOrganizationId() {
        return organizationId;
    }

    @JsonIgnore
    public String getName() {
        return getPrincipal().getId();
    }

    @Override
    protected List<UUID> buildAclKey() {
        return ImmutableList.of( organizationId, getId() );
    }

    @Override
    @JsonIgnore
    public SecurableObjectType getCategory() {
        return SecurableObjectType.Role;
    }

    @Override public String toString() {
        return "RoleKey { " +
                "organizationId=" + organizationId.toString() +
                ", roleId=" + getId().toString() +
                ", title=" + title +
                " }";
    }
}
