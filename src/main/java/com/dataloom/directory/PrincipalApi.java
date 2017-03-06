package com.dataloom.directory;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dataloom.directory.pojo.Auth0UserBasic;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
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

    String SEARCH_EMAIL = SEARCH + EMAIL;

    /*
     * Variable paths
     */

    String USER_ID_PATH            = "/{" + USER_ID + "}";
    String ROLE_PATH               = "/{" + ROLE + "}";
    String SEARCH_QUERY_PATH       = "/{" + SEARCH_QUERY + "}";
    String EMAIL_SEARCH_QUERY_PATH = "/{" + SEARCH_QUERY + ":.+" + "}";

    @GET( BASE + USERS )
    Map<String, Auth0UserBasic> getAllUsers();

    @GET( BASE + USERS + USER_ID_PATH )
    Auth0UserBasic getUser( @Path( USER_ID ) String userId );

    @GET( BASE + USERS + SEARCH + SEARCH_QUERY_PATH )
    Map<String, Auth0UserBasic> searchAllUsers( @Path( SEARCH_QUERY ) String searchQuery );

    @GET( BASE + USERS + SEARCH_EMAIL + EMAIL_SEARCH_QUERY_PATH )
    Map<String, Auth0UserBasic> searchAllUsersByEmail( @Path( SEARCH_QUERY ) String emailSearchQuery );
}
