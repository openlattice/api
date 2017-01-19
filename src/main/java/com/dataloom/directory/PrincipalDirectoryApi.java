package com.dataloom.directory;

import com.dataloom.directory.pojo.Auth0UserBasic;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface PrincipalDirectoryApi {
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

    /*
     * Fixed paths
     */
    String USERS        = "/users";
    String ROLES        = "/roles";
    String RESET        = "/reset";

    /*
     * Variable paths
     */
    
    String USER_ID_PATH = "/{" + USER_ID + "}";
    String ROLE_PATH    = "/{" + ROLE + "}";

    @GET( BASE + USERS )
    Map<String, Auth0UserBasic> getAllUsers();

    @GET( BASE + USERS + USER_ID_PATH )
    Auth0UserBasic getUser( @Path( USER_ID ) String userId );

    @PUT( BASE + USERS + USER_ID_PATH + ROLES + RESET + USER_ID_PATH )
    Void setUserRoles( @Path( USER_ID ) String userId, @Body List<String> roles );

    @PUT( BASE + USERS + USER_ID_PATH + ROLES + ROLE_PATH )
    Void addRoleToUser( @Path( USER_ID ) String userId, @Path( ROLE ) String role );

    @DELETE( BASE + USERS + USER_ID_PATH + ROLES + ROLE_PATH )
    Void removeRoleFromUser( @Path( USER_ID ) String userId, @Path( ROLE ) String role );

    @GET( BASE + ROLES )
    Map<String, List<Auth0UserBasic>> getAllUsersGroupByRole();

    @GET( BASE + ROLES + ROLE_PATH )
    List<Auth0UserBasic> getAllUsersOfRole( @Path( ROLE ) String role );

}
