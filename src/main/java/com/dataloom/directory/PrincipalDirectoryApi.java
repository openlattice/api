package com.dataloom.directory;

import com.dataloom.directory.pojo.Auth0UserBasic;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

import java.util.List;
import java.util.Map;

public interface PrincipalDirectoryApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE      = "/datastore";
    String CONTROLLER   = "/principals";
    String BASE         = SERVICE + CONTROLLER;

    String USER_ID      = "userId";
    String ROLE         = "role";

    String USERS        = "users";
    String ROLES        = "roles";
    String RESET        = "reset";

    String USER_ID_PATH = "{" + USER_ID + "}";
    String ROLE_PATH    = "{" + ROLE + "}";

    @GET( BASE + USERS )
    Map<String, Auth0UserBasic> getAllUsers();

    @GET( BASE + USERS + "／" + USER_ID_PATH )
    Auth0UserBasic getUser( @Path( USER_ID ) String userId );

    @GET( BASE + ROLES )
    Map<String, List<Auth0UserBasic>> getAllUsersGroupByRole();

    @GET( BASE + ROLES + "／" + ROLE_PATH )
    List<Auth0UserBasic> getAllUsersOfRole( @Path( ROLE ) String role );

    @PATCH( BASE + ROLES + "／" + RESET + "／" + USER_ID_PATH )
    Void resetRolesOfUser( @Path( USER_ID ) String userId, @Body List<String> roles );
}
