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
    private final SecurablePrincipal securablePrincipal;
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
            SecurablePrincipal securablePrincipal,
            Set<String> autoApprovedEmails,
            Set<Principal> members,
            Set<Role> roles ) {
        checkArgument( securablePrincipal.getPrincipalType().equals( PrincipalType.ORGANIZATION ) );
        this.securablePrincipal = securablePrincipal;
        this.autoApprovedEmails = checkNotNull( autoApprovedEmails );
        this.members = checkNotNull( members );
        this.roles = checkNotNull( roles );
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public UUID getId() {
        return securablePrincipal.getId();
    }

    @JsonProperty( SerializationConstants.PRINCIPAL )
    public Principal getPrincipal() {
        return securablePrincipal.getPrincipal();
    }

    @JsonProperty( SerializationConstants.TITLE_FIELD )
    public String getTitle() {
        return securablePrincipal.getTitle();
    }

    @JsonProperty( SerializationConstants.DESCRIPTION_FIELD )
    public String getDescription() {
        return securablePrincipal.getDescription();
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

    @Override public boolean equals( Object o ) {
        if ( this == o ) { return true; }
        if ( !( o instanceof Organization ) ) { return false; }

        Organization that = (Organization) o;

        if ( h != that.h ) { return false; }
        if ( !securablePrincipal.equals( that.securablePrincipal ) ) { return false; }
        if ( !autoApprovedEmails.equals( that.autoApprovedEmails ) ) { return false; }
        if ( !members.equals( that.members ) ) { return false; }
        return roles.equals( that.roles );
    }

    @Override public int hashCode() {
        if ( h == 0 ) {
            int result = securablePrincipal.hashCode();
            result = 31 * result + autoApprovedEmails.hashCode();
            result = 31 * result + members.hashCode();
            result = 31 * result + roles.hashCode();
            result = 31 * result + h;
            h = result;
        }

        return h;
    }

    @Override public String toString() {
        return "Organization{" +
                "securablePrincipal=" + securablePrincipal +
                ", autoApprovedEmails=" + autoApprovedEmails +
                ", members=" + members +
                ", roles=" + roles +
                '}';
    }

    public SecurablePrincipal getSecurablePrincipal() {
        return securablePrincipal;
    }
}
