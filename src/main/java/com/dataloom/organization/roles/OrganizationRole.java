package com.dataloom.organization.roles;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * 
 * @author Ho Chung Siu
 *
 */
public class OrganizationRole extends AbstractSecurableObject {
    private static final Logger logger = LoggerFactory.getLogger( OrganizationRole.class );

    private UUID      organizationId;

    private Principal principal;
    private RoleKey   roleKey;

    @JsonCreator
    public OrganizationRole(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.ORGANIZATION_ID ) UUID organizationId,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description ) {
        super( id, title, description );
        this.organizationId = organizationId;
        this.roleKey = new RoleKey( organizationId, this.id );
        this.principal = new Principal( PrincipalType.ROLE, getStringRepresentation( organizationId, title ) );
    }

    @JsonProperty( SerializationConstants.ORGANIZATION_ID )
    public UUID getOrganizationId() {
        return organizationId;
    }

    @JsonIgnore
    public Principal getPrincipal() {
        return principal;
    }

    @JsonIgnore
    public RoleKey getRoleKey() {
        return roleKey;
    }

    /**
     * Convenience method to retrieve acl key for this role
     * @return
     */
    @JsonIgnore
    public List<UUID> getAclKey() {
        return roleKey.getAclKey();
    }

    @Override
    public SecurableObjectType getCategory() {
        return SecurableObjectType.OrganizationRole;
    }
    
    @Override
    public String toString() {
        return getStringRepresentation( organizationId, title );
    }
    
    public static String getStringRepresentation( UUID organizationId, String title ){
        return organizationId + "|" + title;
    }
    
    /**
     * This method must be consistent with {@link #getStringRepresentation(UUID, String)}
     */
    public static UUID getOrganizationId( String stringRep ){
        try{
            String[] splitted = stringRep.split( "|", 2);
            return UUID.fromString( splitted[0] );
        } catch ( Exception e ){
            logger.error( "Error parsing organizationId from the string representation of role: " + stringRep );
            throw new IllegalArgumentException( "Error parsing organizationId from the string representation of role: " + stringRep );
        }
    }
}
