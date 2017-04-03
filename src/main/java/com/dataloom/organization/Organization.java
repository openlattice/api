package com.dataloom.organization;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;
import java.util.UUID;

import com.dataloom.authorization.Principal;
import com.dataloom.authorization.securable.AbstractSecurableObject;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class Organization extends AbstractSecurableObject {
    private final Set<String>    autoApprovedEmails;
    private final Set<Principal> members;
    private final Set<Principal> roles;
    private transient int        h = 0;

    @JsonCreator
    public Organization(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.EMAILS_FIELD ) Set<String> autoApprovedEmails,
            @JsonProperty( SerializationConstants.MEMBERS_FIELD ) Set<Principal> members,
            @JsonProperty( SerializationConstants.ROLES ) Set<Principal> roles ) {
        super( id, title, description );
        this.autoApprovedEmails = checkNotNull( autoApprovedEmails );
        this.members = checkNotNull( members );
        this.roles = checkNotNull( roles );
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
    public Set<Principal> getRoles() {
        return roles;
    }

    @Override
    @JsonIgnore
    public SecurableObjectType getCategory() {
        return SecurableObjectType.Organization;
    }

    @Override
    public int hashCode() {
        if ( h == 0 ) {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + ( ( autoApprovedEmails == null ) ? 0 : autoApprovedEmails.hashCode() );
            result = prime * result + ( ( members == null ) ? 0 : members.hashCode() );
            result = prime * result + ( ( roles == null ) ? 0 : roles.hashCode() );
            h = result;
        }
        return h;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( !super.equals( obj ) ) {
            return false;
        }
        if ( !( obj instanceof Organization ) ) {
            return false;
        }
        Organization other = (Organization) obj;
        if ( autoApprovedEmails == null ) {
            if ( other.autoApprovedEmails != null ) {
                return false;
            }
        } else if ( !autoApprovedEmails.equals( other.autoApprovedEmails ) ) {
            return false;
        }
        if ( members == null ) {
            if ( other.members != null ) {
                return false;
            }
        } else if ( !members.equals( other.members ) ) {
            return false;
        }
        if ( roles == null ) {
            if ( other.roles != null ) {
                return false;
            }
        } else if ( !roles.equals( other.roles ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Organization [autoApprovedEmails="
                + autoApprovedEmails + ", members=" + members + ", roles=" + roles + "]";
    }

}
