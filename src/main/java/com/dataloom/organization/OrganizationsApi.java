package com.dataloom.organization;

import java.util.Set;
import java.util.UUID;

import com.dataloom.authorization.Principal;
import com.dataloom.directory.pojo.Auth0UserBasic;
import com.dataloom.organization.roles.Role;

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
    String EMAIL_DOMAIN_PATH = "/{" + EMAIL_DOMAIN + ":.+}";
    String PRINCIPALS        = "/principals";
    String PRINCIPAL_ID      = "pid";
    String PRINCIPAL_ID_PATH = "/{" + PRINCIPAL_ID + "}";
    String TYPE              = "type";
    String TYPE_PATH         = "/{" + TYPE + "}";
    String ROLES             = "/roles";
    String MEMBERS           = "/members";

    String ROLE_ID           = "roleId";
    String ROLE_ID_PATH      = "/{" + ROLE_ID + "}";
    String USER_ID           = "userId";
    String USER_ID_PATH      = "/{" + USER_ID + "}";

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

    //Endpoints about members
    @GET( BASE + ID_PATH + PRINCIPALS + MEMBERS )
    Set<Principal> getMembers( @Path( ID ) UUID organizationId );

    @PUT( BASE + ID_PATH + PRINCIPALS + MEMBERS + USER_ID_PATH )
    Void addMember( @Path( ID ) UUID organizationId, @Path( USER_ID ) String userId );

    @DELETE( BASE + ID_PATH + PRINCIPALS + MEMBERS + USER_ID_PATH )
    Void removeMember( @Path( ID ) UUID organizationId, @Path( USER_ID ) String userId );

    // Endpoints about roles
    @POST( BASE + ROLES )
    UUID createRole( @Body Role role );

    @GET( BASE + ID_PATH + PRINCIPALS + ROLES )
    Set<Role> getRoles( @Path( ID ) UUID organizationId );

    @GET( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH )
    Role getRole( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId );

    @PUT( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH + TITLE )
    Void updateRoleTitle( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId, @Body String title );

    @PUT( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH + DESCRIPTION )
    Void updateRoleDescription( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId, @Body String description );

    @DELETE( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH )
    Void deleteRole( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId );

    @GET( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH + MEMBERS )
    Iterable<Auth0UserBasic> getAllUsersOfRole( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId );

    @PUT( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH + MEMBERS + USER_ID_PATH )
    Void addRoleToUser( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId, @Path( USER_ID ) String userId );

    @DELETE( BASE + ID_PATH + PRINCIPALS + ROLES + ROLE_ID_PATH + MEMBERS + USER_ID_PATH )
    Void removeRoleFromUser( @Path( ID ) UUID organizationId, @Path( ROLE_ID ) UUID roleId, @Path( USER_ID ) String userId );

}
