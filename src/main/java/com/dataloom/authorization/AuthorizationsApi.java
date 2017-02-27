package com.dataloom.authorization;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    String PAGING_STATE = "next";

    @POST( BASE )
    Iterable<Authorization> checkAuthorizations( @Body Set<AccessCheck> queries );

    @GET( BASE )
    Iterable<List<UUID>> getAccessibleObjects(
            @Query( OBJECT_TYPE ) SecurableObjectType type,
            @Query( PERMISSION ) Permission permission );

}
