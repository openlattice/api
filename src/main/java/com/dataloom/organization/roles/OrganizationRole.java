package com.dataloom.organization.roles;

import com.dataloom.authorization.securable.AbstractSecurableObject;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A role in an organization. Use both organizationId and roleId to specify a role.
 *
 * @author Ho Chung Siu
 */
public class OrganizationRole extends AbstractSecurableObject {
    private static final Logger logger = LoggerFactory.getLogger( OrganizationRole.class );

    private RoleKey roleKey;

    @JsonCreator
    public OrganizationRole(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.ORGANIZATION_ID ) UUID organizationId,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description ) {
        super( id, title, description );
        this.organizationId = organizationId;
        this.roleKey = new RoleKey( organizationId, this.id );
    }

    @Override public String toString() {
        return "OrganizationRole{" +
                "roleKey=" + roleKey +
                '}';
    }

    @JsonProperty( SerializationConstants.ORGANIZATION_ID )
    public UUID getOrganizationId() {
        return roleKey.getOrganizationId();
    }

    @JsonIgnore
    public RoleKey getRoleKey() {
        return roleKey;
    }

    /**
     * Convenience method to retrieve acl key for this role
     */
    @JsonIgnore
    public List<UUID> getAclKey() {
        return roleKey.getAclKey();
    }

    @Override
    @JsonIgnore
    public SecurableObjectType getCategory() {
        return SecurableObjectType.OrganizationRole;
    }

}
