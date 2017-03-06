package com.dataloom.organization.roles;

import java.util.UUID;

import com.dataloom.authorization.Principal;
import com.dataloom.authorization.PrincipalType;
import com.dataloom.authorization.securable.AbstractSecurableObject;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * A role in an organization. Use both organizationId and roleId to specify a role.
 * @author Ho Chung Siu
 *
 */
public class OrganizationRole extends AbstractSecurableObject {
    private UUID      organizationId;

    private Principal principal;

    @JsonCreator
    public OrganizationRole( 
            @JsonProperty( SerializationConstants.ORGANIZATION_ID ) UUID organizationId, 
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title, 
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description ) {
        this( Optional.absent(), organizationId, title, description );
    }

    public OrganizationRole( Optional<UUID> id, UUID organizationId, String title, Optional<String> description ){
        super( id, title, description );
        this.organizationId = organizationId;
        this.principal = new Principal( PrincipalType.ROLE, this.id.toString() );        
    }
    
    @JsonProperty( SerializationConstants.ORGANIZATION_ID )
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
