package com.dataloom.directory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.dataloom.directory.pojo.Auth0UserBasic;
import com.dataloom.organization.roles.OrganizationRole;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PrincipalApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE      = "/datastore";
    String CONTROLLER   = "/principals";
    String BASE         = SERVICE + CONTROLLER;

    /*
     * Path variables
     */
    String USER_ID      = "userId";
    String ROLE         = "role";
    String SEARCH_QUERY = "searchQuery";

    /*
     * Fixed paths
     */
    String EMAIL        = "/email";
    String RESET        = "/reset";
    String ROLES        = "/roles";
    String SEARCH       = "/search";
    String USERS        = "/users";
    String TITLE       = "/title";
    String DESCRIPTION        = "/description";

    String SEARCH_EMAIL = SEARCH + EMAIL;

    /*
     * Variable paths
     */

    String USER_ID_PATH            = "/{" + USER_ID + "}";
    String ROLE_PATH               = "/{" + ROLE + "}";
    String SEARCH_QUERY_PATH       = "/{" + SEARCH_QUERY + "}";
    String EMAIL_SEARCH_QUERY_PATH = "/{" + SEARCH_QUERY + ":.+" + "}";

    // Endpoints about users
    @GET( BASE + USERS )
    Map<String, Auth0UserBasic> getAllUsers();

    @GET( BASE + USERS + USER_ID_PATH )
    Auth0UserBasic getUser( @Path( USER_ID ) String userId );

    @PUT( BASE + USERS + USER_ID_PATH + ROLES + ROLE_PATH )
    Void addRoleToUser( @Path( USER_ID ) String userId, @Path( ROLE ) String roleIdString );

    @DELETE( BASE + USERS + USER_ID_PATH + ROLES + ROLE_PATH )
    Void removeRoleFromUser( @Path( USER_ID ) String userId, @Path( ROLE ) String roleIdString );

    @GET( BASE + USERS + SEARCH + SEARCH_QUERY_PATH )
    Map<String, Auth0UserBasic> searchAllUsers( @Path( SEARCH_QUERY ) String searchQuery );

    @GET( BASE + USERS + SEARCH_EMAIL + EMAIL_SEARCH_QUERY_PATH )
    Map<String, Auth0UserBasic> searchAllUsersByEmail( @Path( SEARCH_QUERY ) String emailSearchQuery );
    
    // Endpoints about roles
    @POST( BASE + ROLES )
    String createRole( @Body OrganizationRole role );

    @GET( BASE + ROLES )
    Iterable<OrganizationRole> getAllRoles();
    
    @GET( BASE + ROLES + ROLE_PATH )
    List<Auth0UserBasic> getAllUsersOfRole( @Path( ROLE ) String roleIdString );

    @PUT( BASE + ROLES + ROLE_PATH + TITLE )
    Void updateTitle( @Path( ROLE ) String roleIdString, @Body String title );

    @PUT( BASE + ROLES + ROLE_PATH + DESCRIPTION )
    Void updateDescription( @Path( ROLE ) String roleIdString, @Body String description );

    @DELETE( BASE + ROLES + ROLE_PATH )
    Void deleteRole( @Path( ROLE ) String roleIdStrin );
    
    
}
