package com.dataloom.organization.roles;

import java.util.List;
import java.util.UUID;

import com.dataloom.edm.internal.DatastoreConstants;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dataloom.authorization.securable.AbstractSecurableObject;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * A role in an organization. Use both organizationId and roleId to specify a role.
 */
public class Role extends AbstractSecurableObject {

    private static final Logger logger = LoggerFactory.getLogger( Role.class );

    private final String  reservationName;
    private final RoleKey roleKey;

    @JsonCreator
    public Role(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.ORGANIZATION_ID ) UUID organizationId,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description ) {

        super( id, title, description );

        this.roleKey = new RoleKey( organizationId, this.id );
        this.reservationName = organizationId + "|" + title;
    }

    @JsonProperty( SerializationConstants.ORGANIZATION_ID )
    public UUID getOrganizationId() {
        return roleKey.getOrganizationId();
    }

    @JsonIgnore
    public RoleKey getRoleKey() {
        return roleKey;
    }

    @JsonIgnore
    public List<UUID> getAclKey() {
        return roleKey.getAclKey();
    }

    @JsonIgnore
    public String getReservationName() {
        return reservationName;
    }

    @Override
    @JsonIgnore
    public SecurableObjectType getCategory() {
        return SecurableObjectType.Role;
    }

    @Override public String toString() {
        return "RoleKey { " +
                "organizationId=" + roleKey.getOrganizationId() +
                ", roleId=" + roleKey.getRoleId() +
                ", title=" + title +
                " }";
    }
}
