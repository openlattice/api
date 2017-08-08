package com.dataloom.organization.roles;

import java.util.List;
import java.util.UUID;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dataloom.authorization.securable.AbstractSecurableObject;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class Role extends AbstractSecurableObject {

    private static final Logger logger = LoggerFactory.getLogger( Role.class );

    private final String  reservationName;
    private final RoleKey roleKey;

    public static String generateReservationName( UUID organizationId, String title ) {

        Preconditions.checkNotNull( organizationId );
        Preconditions.checkArgument( StringUtils.isNotBlank( title ) );

        return organizationId + "|" + title;
    }

    @JsonCreator
    public Role(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.ORGANIZATION_ID ) UUID organizationId,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description ) {

        super( id, title, description );
        this.roleKey = new RoleKey( organizationId, this.id );

        /*
         * a Role is uniquely identified by both organizationId and roleId, but AclKey reservation only works with a
         * single UUID. since we can't use both organizationId and roleId for AclKey reservation, we need a unique
         * String to identify a Role for the UUID <-> String mapping. this should be thought of as a hack since we
         * really need a List<UUID> <-> String mapping.
         */
        this.reservationName = Role.generateReservationName( organizationId, title );
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
