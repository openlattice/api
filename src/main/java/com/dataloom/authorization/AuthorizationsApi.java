package com.dataloom.authorization;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.dataloom.authorization.paging.AuthorizedObjectsSearchResult;
import com.dataloom.authorization.securable.SecurableObjectType;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthorizationsApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE      = "/datastore";
    String CONTROLLER   = "/authorizations";
    String BASE         = SERVICE + CONTROLLER;

    String OBJECT_TYPE  = "objectType";
    String PERMISSION   = "permission";
    String PAGING_TOKEN = "pagingToken";

    @POST( BASE )
    Iterable<Authorization> checkAuthorizations( @Body Set<AccessCheck> queries );

    /**
     * Returns paged results for all authorized objects of specified objectType, that the current user has specified permission for. 
     * @param objectType Required field. Specifying the Securable Object Type that user wants to search for.
     * @param permission Required field. Specifying the permission the user must have for the accessible objects.
     * @param pagingToken Unrequired field. One may use the paging token from previous search result to get to the next page of results.
     * @return
     */
    @GET( BASE )
    AuthorizedObjectsSearchResult getAccessibleObjects(
            @Query( OBJECT_TYPE ) SecurableObjectType objectType,
            @Query( PERMISSION ) Permission permission,
            @Query( PAGING_TOKEN ) String pagingToken );

}
