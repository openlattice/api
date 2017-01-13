package com.dataloom.organization;

import com.dataloom.authorization.Principal;
import com.dataloom.authorization.PrincipalType;
import retrofit2.http.*;

import java.util.Set;
import java.util.UUID;

public interface OrganizationsApi {
    String ORGANIZATIONS     = "organizations";
    String ID                = "id";
    String ID_PATH           = "/{" + ID + "}";
    String DESCRIPTION       = "/description";
    String TITLE             = "/title";
    String EMAIL_DOMAIN      = "email-domain";
    String EMAIL_DOMAINS     = "/email-domains";
    String EMAIL_DOMAIN_PATH = "/{" + EMAIL_DOMAIN + "}";
    String PRINCIPALS        = "/principals";
    String PRINCIPAL_ID      = "pid";
    String PRINCIPAL_ID_PATH = "/{" + PRINCIPAL_ID + "}";
    String TYPE              = "type";
    String TYPE_PATH         = "/{" + TYPE + "}";
    String ROLES             = "/roles";
    String MEMBERS           = "/members";

    @GET( ORGANIZATIONS )
    Iterable<Organization> getOrganizations();

    @POST( ORGANIZATIONS )
    UUID createOrganizationIfNotExists( @Body Organization organization );

    @GET( ORGANIZATIONS + ID_PATH )
    Organization getOrganization( @Path( ID ) UUID organizationId );

    @DELETE( ORGANIZATIONS + ID_PATH )
    Void destroyOrganization( @Path( ID ) UUID organizationId );

    @PUT( ORGANIZATIONS + ID_PATH + TITLE )
    Void updateTitle( @Path( ID ) UUID organziationId, @Body String title );

    @PUT( ORGANIZATIONS + ID_PATH + DESCRIPTION )
    Void updateDescription( @Path( ID ) UUID organizationId, @Body String description );

    @GET( ORGANIZATIONS + ID_PATH + EMAIL_DOMAINS )
    Set<String> getAutoApprovedEmailDomains( @Path( ID ) UUID organizationId );

    @PUT( ORGANIZATIONS + ID_PATH + EMAIL_DOMAINS )
    Void setAutoApprovedEmailDomain( @Path( ID ) UUID organizationId, @Body Set<String> emailDomain );

    /**
     * Add multiple e-mail domains to the auto-approval list.
     *
     * @param organizationId The id of the organization to modify.
     * @param emailDomains   The e-mail domain to add to the auto-approval list.
     */
    @POST( ORGANIZATIONS + ID_PATH + EMAIL_DOMAINS )
    Void addAutoApprovedEmailDomains( @Path( ID ) UUID organizationId, @Body Set<String> emailDomains );

    @HTTP( method = "DELETE", hasBody = true, path = ORGANIZATIONS + ID_PATH + EMAIL_DOMAINS )
    Void removeAutoApprovedEmailDomains( @Path( ID ) UUID organizationId, @Body Set<String> emailDomain );

    /**
     * Adds a single e-mail domain to the auto-approval list. This will fail for users who are not an owner of the
     * organization.
     *
     * @param organizationId The id of the organization to modify.
     * @param emailDomain    The e-mail domain to add to the auto-approval list.
     */
    @PUT( ORGANIZATIONS + ID_PATH + EMAIL_DOMAINS + EMAIL_DOMAIN_PATH )
    Void addAutoApprovedEmailDomain( @Path( ID ) UUID organizationId, @Path( EMAIL_DOMAIN ) String emailDomain );

    /**
     * Removes a single e-mail domain to the auto-approval list. This will fail for users who are not an owner of the
     * organization.
     *
     * @param organizationId The id of the organization to modify.
     * @param emailDomain    The e-mail domain to add to the auto-approval list.
     */
    @DELETE( ORGANIZATIONS + ID_PATH + EMAIL_DOMAINS + EMAIL_DOMAIN_PATH )
    Void removeAutoApprovedEmailDomain( @Path( ID ) UUID organizationId, @Path( EMAIL_DOMAIN ) String emailDomain );

    @GET( ORGANIZATIONS + ID_PATH + PRINCIPALS )
    Set<Principal> getPrincipals( @Path( ID ) UUID organizationId );

    /**
     * This is a convenience call that modifies that grants {@code {@link com.dataloom.authorization.Permission#READ}}
     * on the specified organization to the specified principals. A user's membership in an organization is determined
     * by whether or not they have {@code {@link com.dataloom.authorization.Permission#READ}} on the organization.
     *
     * @param organizationId The id of the organization.
     * @param principals     A set of valid principals.
     */
    @POST( ORGANIZATIONS + ID_PATH + PRINCIPALS )
    Void addPrincipals( @Path( ID ) UUID organizationId, @Body Set<Principal> principals );

    /**
     * This is a convenience call that modifies that grants {@code {@link com.dataloom.authorization.Permission#READ}}
     * on the specified organization to the specified principals. A user's membership in an organization is determined
     * by whether or not they have {@code {@link com.dataloom.authorization.Permission#READ}} on the organization.
     *
     * @param organizationId The id of the organization.
     * @param principals     A set of valid principals.
     */
    @PUT( ORGANIZATIONS + ID_PATH + PRINCIPALS )
    Void addPrincipals( @Path( ID ) UUID organizationId, @Body Set<Principal> principals );

    /**
     * This is a convenience call that modifies that removes all {@code {@link com.dataloom.authorization.Permission}}s
     * that a principal may have on an organization, including discover.
     *
     * @param organizationId The id of the organization.
     * @param principals     A set of valid principals
     */
    @HTTP( method = "DELETE", hasBody = true, path = ORGANIZATIONS + ID_PATH + PRINCIPALS )
    Void removePrincipals( @Path( ID ) UUID organizationId, @Body Set<Principal> principals );

    @GET( ORGANIZATIONS + ID_PATH + PRINCIPALS + ROLES )
    Set<Principal> getRoles( @Path( ID ) UUID organizationId );

    @GET( ORGANIZATIONS + ID_PATH + PRINCIPALS + MEMBERS )
    Set<Principal> getMembers( @Path( ID ) UUID organizationId );

    /**
     * This is a convenience call that grants {@code {@link com.dataloom.authorization.Permission#READ}}
     * to a principal on an organization.
     *
     * @param organizationId The id of the organization.
     * @param principalType  The principal type of the principal being added.
     * @param principalId    The principalId of the principal being added.
     */
    @PUT( ORGANIZATIONS + ID_PATH + PRINCIPALS + TYPE_PATH + PRINCIPAL_ID_PATH )
    Void addPrincipal(
            @Path( ID ) UUID organizationId,
            @Path( TYPE ) PrincipalType principalType,
            @Path( PRINCIPAL_ID ) String principalId );

    /**
     * This is a convenience call that removes all {@code {@link com.dataloom.authorization.Permission}}s
     * that a principal may have on an organization, including discover.
     *
     * @param organizationId The id of the organization.
     * @param principalType  The principal type of the principal being added.
     * @param principalId    The principalId of the principal being added.
     */
    @DELETE( ORGANIZATIONS + ID_PATH + PRINCIPALS + TYPE_PATH + PRINCIPAL_ID_PATH )
    Void removePrincipal(
            @Path( ID ) UUID organizationId,
            @Path( TYPE ) PrincipalType principalType,
            @Path( PRINCIPAL_ID ) String principalId );

}
