package com.dataloom.organization;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;
import java.util.UUID;

import com.dataloom.authorization.Principal;
import com.dataloom.authorization.SecurableObjectType;
import com.dataloom.data.SerializationConstants;
import com.dataloom.edm.internal.AbstractSecurableObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class Organization extends AbstractSecurableObject {

    private static final long    serialVersionUID = -669072251620432197L;
    private final Set<UUID>      trustedOrganizations;
    private final Set<String>    autoApprovedEmails;
    private final Set<Principal> members;
    private final Set<Principal> roles;

    @JsonCreator
    public Organization(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.TRUSTED_ORGANIZATIONS_FIELD ) Set<UUID> trustedOrganizations,
            @JsonProperty( SerializationConstants.EMAILS_FIELD ) Set<String> autoApprovedEmails,
            @JsonProperty( SerializationConstants.MEMBERS_FIELD ) Set<Principal> members,
            @JsonProperty( SerializationConstants.ROLES ) Set<Principal> roles ) {
        super( id, title, description );
        this.trustedOrganizations = checkNotNull( trustedOrganizations );
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

    @JsonProperty( SerializationConstants.TRUSTED_ORGANIZATIONS_FIELD )
    public Set<UUID> getTrustedOrganizations() {
        return trustedOrganizations;
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

}
