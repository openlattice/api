package com.dataloom.organization;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.dataloom.authorization.Principal;
import com.dataloom.authorization.PrincipalType;
import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.organization.roles.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.openlattice.authorization.SecurablePrincipal;
import java.util.Set;
import java.util.UUID;

public class Organization {
    private final SecurablePrincipal principal;
    private final Set<String>        autoApprovedEmails;
    private final Set<Principal>     members;
    private final Set<Role>          roles;
    private transient int h = 0;

    @JsonCreator
    public Organization(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.PRINCIPAL ) Principal principal,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.EMAILS_FIELD ) Set<String> autoApprovedEmails,
            @JsonProperty( SerializationConstants.MEMBERS_FIELD ) Set<Principal> members,
            @JsonProperty( SerializationConstants.ROLES ) Set<Role> roles ) {
        this( new SecurablePrincipal( id, principal, title, description ), autoApprovedEmails, members, roles );
        //        checkArgument( principal.getType().equals( PrincipalType.ORGANIZATION ) );
        //        this.principal = new SecurablePrincipal( id, principal, title, description );
        //        this.autoApprovedEmails = checkNotNull( autoApprovedEmails );
        //        this.members = checkNotNull( members );
        //        this.roles = checkNotNull( roles );
    }

    public Organization(
            SecurablePrincipal principal,
            Set<String> autoApprovedEmails,
            Set<Principal> members,
            Set<Role> roles ) {
        checkArgument( principal.getPrincipalType().equals( PrincipalType.ORGANIZATION ) );
        this.principal = principal;
        this.autoApprovedEmails = checkNotNull( autoApprovedEmails );
        this.members = checkNotNull( members );
        this.roles = checkNotNull( roles );
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public UUID getId() {
        return principal.getId();
    }
    
    @JsonProperty( SerializationConstants.TITLE_FIELD )
    public String getTitle() {
        return principal.getTitle();
    }

    @JsonProperty( SerializationConstants.DESCRIPTION_FIELD )
    public String getDescription() {
        return principal.getDescription();
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

    public SecurablePrincipal getPrincipal() {
        return principal;
    }
}
