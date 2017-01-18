package com.dataloom.authorization;

import java.util.Set;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthorizationsApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE                 = "/datastore";
    String CONTROLLER              = "/authorizations";
    String BASE                    = SERVICE + CONTROLLER;
    
    @POST( BASE )
    Iterable<Authorization> checkAuthorizations( @Body Set<AccessCheck> queries );
}
