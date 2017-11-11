package com.dataloom.organization;

import com.dataloom.authorization.Principal;
import com.dataloom.authorization.securable.AbstractSecurableObject;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.organization.roles.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import java.util.Set;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class Organization extends AbstractSecurableObject {
    private final Set<String> autoApprovedEmails;
    private final Set<Principal> members;
    private final Set<Role> roles;
    private final Set<UUID> apps;
    private transient int h = 0;

    @JsonCreator
    public Organization(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.EMAILS_FIELD ) Set<String> autoApprovedEmails,
            @JsonProperty( SerializationConstants.MEMBERS_FIELD ) Set<Principal> members,
            @JsonProperty( SerializationConstants.ROLES ) Set<Role> roles,
            @JsonProperty( SerializationConstants.APPS ) Set<UUID> apps ) {
        super( id, title, description );
        this.autoApprovedEmails = checkNotNull( autoApprovedEmails );
        this.members = checkNotNull( members );
        this.roles = checkNotNull( roles );
        this.apps = checkNotNull( apps );
    }

    public Organization(
            Optional<UUID> id,
            String title,
            Optional<String> description,
            Set<String> autoApprovedEmails,
            Set<Principal> members,
            Set<Role> roles ) {
        this( id, title, description, autoApprovedEmails, members, roles, ImmutableSet.of() );
    }

    @JsonProperty( SerializationConstants.TITLE_FIELD )
    public String getTitle() {
        return title;
    }

    @JsonProperty( SerializationConstants.DESCRIPTION_FIELD )
    public String getDescription() {
        return description;
    }

    @JsonProperty( SerializationConstants.EMAILS_FIELD )
    public Set<String> getAutoApprovedEmails() {
        return autoApprovedEmails;
    }

    @JsonProperty( SerializationConstants.MEMBERS_FIELD )
    public Set<Principal> getMembers() {
        return members;
    }

    @JsonProperty( SerializationConstants.ROLES )
    public Set<Role> getRoles() {
        return roles;
    }

    @Override public String toString() {
        return "Organization{" +
                "autoApprovedEmails=" + autoApprovedEmails +
                ", members=" + members +
                ", roles=" + roles +
                ", apps=" + apps +
                ", h=" + h +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        if ( !super.equals( o ) )
            return false;

        Organization that = (Organization) o;

        if ( h != that.h )
            return false;
        if ( !autoApprovedEmails.equals( that.autoApprovedEmails ) )
            return false;
        if ( !members.equals( that.members ) )
            return false;
        if ( !roles.equals( that.roles ) )
            return false;
        return apps.equals( that.apps );
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + autoApprovedEmails.hashCode();
        result = 31 * result + members.hashCode();
        result = 31 * result + roles.hashCode();
        result = 31 * result + apps.hashCode();
        result = 31 * result + h;
        return result;
    }

    @JsonProperty( SerializationConstants.APPS )
    public Set<UUID> getApps() {
        return apps;
    }

    @Override
    @JsonIgnore
    public SecurableObjectType getCategory() {
        return SecurableObjectType.Organization;
    }

}
