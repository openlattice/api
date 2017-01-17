package com.dataloom.authorization;

import java.util.Set;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthorizationsApi {
    String AUTHORIZATIONS = "authorizations";
    
    @POST( AUTHORIZATIONS )
    Iterable<Authorization> checkAuthorizations( @Body Set<AccessCheck> queries );
}
