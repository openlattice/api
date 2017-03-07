package com.dataloom.organization;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.dataloom.authorization.Principal;
import com.dataloom.authorization.PrincipalType;
import com.dataloom.directory.pojo.Auth0UserBasic;
import com.dataloom.organization.roles.OrganizationRole;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrganizationsApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE           = "/datastore";
    String CONTROLLER        = "/organizations";
    String BASE              = SERVICE + CONTROLLER;

    /*
     * Acutal path elements
     */
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
    
    String ROLE_ID           = "roleId";
    String ROLE_ID_PATH      = "/{" + ROLE_ID + "}";

    @GET( BASE )
    Iterable<Organization> getOrganizations();

    @POST( BASE )
    UUID createOrganizationIfNotExists( @Body Organization organization );

    @GET( BASE + ID_PATH )
    Organization getOrganization( @Path( ID ) UUID organizationId );

    @DELETE( BASE + ID_PATH )
    Void destroyOrganization( @Path( ID ) UUID organizationId );

    @PUT( BASE + ID_PATH + TITLE )
    Void updateTitle( @Path( ID ) UUID organziationId, @Body String title );

    @PUT( BASE + ID_PATH + DESCRIPTION )
    Void updateDescription( @Path( ID ) UUID organizationId, @Body String description );

    @GET( BASE + ID_PATH + EMAIL_DOMAINS )
    Set<String> getAutoApprovedEmailDomains( @Path( ID ) UUID organizationId );

    @PUT( BASE + ID_PATH + EMAIL_DOMAINS )
    Void setAutoApprovedEmailDomain( @Path( ID ) UUID organizationId, @Body Set<String> emailDomain );

    /**
     * Add multiple e-mail domains to the auto-approval list.
     *
     * @param organizationId The id of the organization to modify.
     * @param emailDomains The e-mail domain to add to the auto-approval list.
     */
    @POST( BASE + ID_PATH + EMAIL_DOMAINS )
    Void addAutoApprovedEmailDomains( @Path( ID ) UUID organizationId, @Body Set<String> emailDomains );

    @HTTP(
        method = "DELETE",
        hasBody = true,
        path = BASE + ID_PATH + EMAIL_DOMAINS )
    Void removeAutoApprovedEmailDomains( @Path( ID ) UUID organizationId, @Body Set<String> emailDomain );

    /**
     * Adds a single e-mail domain to the auto-approval list. This will fail for users who are not an owner of the
     * organization.
     *
     * @param organizationId The id of the organization to modify.
     * @param emailDomain The e-mail domain to add to the auto-approval list.
     */
    @PUT( BASE + ID_PATH + EMAIL_DOMAINS + EMAIL_DOMAIN_PATH )
    Void addAutoApprovedEmailDomain( @Path( ID ) UUID organizationId, @Path( EMAIL_DOMAIN ) String emailDomain );

    /**
     * Removes a single e-mail domain to the auto-approval list. This will fail for users who are not an owner of the
     * organization.
     *
     * @param organizationId The id of the organization to modify.
     * @param emailDomain The e-mail domain to add to the auto-approval list.
     */
    @DELETE( BASE + ID_PATH + EMAIL_DOMAINS + EMAIL_DOMAIN_PATH )
    Void removeAutoApprovedEmailDomain( @Path( ID ) UUID organizationId, @Path( EMAIL_DOMAIN ) String emailDomain );

    @GET( BASE + ID_PATH + PRINCIPALS )
    Set<Principal> getPrincipals( @Path( ID ) UUID organizationId );

    /**
     * This is a convenience call that modifies that grants {@code {@link com.dataloom.authorization.Permission#READ}}
     * on the specified organization to the specified principals. A user's membership in an organization is determined
     * by whether or not they have {@code {@link com.dataloom.authorization.Permission#READ}} on the organization.
     *
     * @param organizationId The id of the organization.
     * @param principals A set of valid principals.
     */
    @POST( BASE + ID_PATH + PRINCIPALS )
    Void addPrincipals( @Path( ID ) UUID organizationId, @Body Set<Principal> principals );

    /**
     * This is a convenience call that modifies that grants {@code {@link com.dataloom.authorization.Permission#READ}}
     * on the specified organization to the specified principals. A user's membership in an organization is determined
     * by whether or not they have {@code {@link com.dataloom.authorization.Permission#READ}} on the organization.
     *
     * @param organizationId The id of the organization.
     * @param principals A set of valid principals.gs
     * 
     */
    @PUT( BASE + ID_PATH + PRINCIPALS )
    Void setPrincipals( @Path( ID ) UUID organizationId, @Body Set<Principal> principals );

    /**
     * This is a convenience call that modifies that removes all {@code {@link com.dataloom.authorization.Permission}}s
     * that a principal may have on an organization, including discover.
     *
     * @param organizationId The id of the organization.
     * @param principals A set of valid principals
     */
    @HTTP(
        method = "DELETE",
        hasBody = true,
        path = BASE + ID_PATH + PRINCIPALS )
    Void removePrincipals( @Path( ID ) UUID organizationId, @Body Set<Principal> principals );

    @GET( BASE + ID_PATH + PRINCIPALS + MEMBERS )
    Set<Principal> getMembers( @Path( ID ) UUID organizationId );

    /**
     * This is a convenience call that grants {@code {@link com.dataloom.authorization.Permission#READ}} to a principal
     * on an organization.
     *
     * @param organizationId The id of the organization.
     * @param principalType The principal type of the principal being added.
     * @param principalId The principalId of the principal being added.
     */
    @PUT( BASE + ID_PATH + PRINCIPALS + TYPE_PATH + PRINCIPAL_ID_PATH )
    Void addPrincipal(
            @Path( ID ) UUID organizationId,
            @Path( TYPE ) PrincipalType principalType,
            @Path( PRINCIPAL_ID ) String principalId );

    /**
     * This is a convenience call that removes all {@code {@link com.dataloom.authorization.Permission}}s that a
     * principal may have on an organization, including discover.
     *
     * @param organizationId The id of the organization.
     * @param principalType The principal type of the principal being added.
     * @param principalId The principalId of the principal being added.
     */
    @DELETE( BASE + ID_PATH + PRINCIPALS + TYPE_PATH + PRINCIPAL_ID_PATH )
    Void removePrincipal(
            @Path( ID ) UUID organizationId,
            @Path( TYPE ) PrincipalType principalType,
            @Path( PRINCIPAL_ID ) String principalId );
    
    // Endpoints about roles
    @POST( BASE + ROLES )
    UUID createRole( @Body OrganizationRole role );

    @GET( BASE + ID_PATH + PRINCIPALS + ROLES )
    Iterable<Principal> getRoles( @Path( ID ) UUID organizationId );

    @GET( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH )
    OrganizationRole getRole( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId );

    @PUT( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH + TITLE )
    Void updateRoleTitle( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId, @Body String title );

    @PUT( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH + DESCRIPTION )
    Void updateRoleDescription( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId, @Body String description );

    @DELETE( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH )
    Void deleteRole( @Path( ID )UUID organizationId, @Path( ROLE_ID ) UUID roleId );    

    @GET( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH + MEMBERS )
    Iterable<Auth0UserBasic> getAllUsersOfRole( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId );

    @PUT( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH + MEMBERS + PRINCIPAL_ID_PATH )
    Void addRoleToUser( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId, @Path( PRINCIPAL_ID ) String userId );

    @DELETE( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH + MEMBERS + PRINCIPAL_ID_PATH )
    Void removeRoleFromUser( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId, @Path( PRINCIPAL_ID ) String userId );

}
