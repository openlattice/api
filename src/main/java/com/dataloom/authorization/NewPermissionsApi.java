package com.dataloom.authorization;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface NewPermissionsApi {
    String PERMISSIONS = "permissions";

    /**
     * Add, removes, or sets the ace for a particular acl key. Successful only if user is the owner of acl key.
     * 
     * @param req The acl key, the principals, and the aces to set for that particular ace key.
     * @return The aces for the acl key, after applying the request changes.
     */
    @PATCH( PERMISSIONS )
    Acl updateAcl( @Body AclData req );

    /**
     * Retrieves the acl for a particular acl key. Only return if user is the owner of acl key.
     * 
     * @param aclKeys The acl key.
     * @return The aces for the requested acl key.
     */
    @POST( PERMISSIONS )
    Acl getAcl( @Body List<AclKey> aclKeys );
}
