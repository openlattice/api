package com.dataloom.authorization;

import java.util.Set;

import retrofit2.http.POST;

public interface AuthorizationsApi {
    String AUTHORIZATIONS = "authorizations";
    
    @POST( AUTHORIZATIONS )
    Iterable<Auth> checkAuthorizations( Set<AuthQuery> queries );
}
