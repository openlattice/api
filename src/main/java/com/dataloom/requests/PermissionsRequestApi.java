package com.dataloom.requests;

import java.util.List;
import java.util.UUID;

import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PermissionsRequestApi {
    String PERMISSIONS = "requests";
    String ADMIN       = "admin";
    String UNRESOLVED  = "unresolved";
    String RESOLVED    = "resolved";

    @PUT( PERMISSIONS )
    Void upsertRequest( AclRootRequestDetailsPair req );

    @POST( PERMISSIONS + "/" + UNRESOLVED )
    PermissionsRequest getUnresolvedRequestOfUser( List<UUID> aclRoot );

    @POST( PERMISSIONS + "/" + RESOLVED )
    Iterable<PermissionsRequest> getResolvedRequestsOfUser( List<UUID> aclRoot );

    @POST( PERMISSIONS + "/" + ADMIN )
    Void updateUnresolvedRequestStatus( PermissionsRequest req );

    @POST( PERMISSIONS + "/" + ADMIN + "/" + UNRESOLVED )
    Iterable<PermissionsRequest> getAllUnresolvedRequestsOfAdmin( AclRootStatusPair req );

}
