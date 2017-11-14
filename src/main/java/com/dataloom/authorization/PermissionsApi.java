package com.dataloom.authorization;

import java.util.List;
import java.util.UUID;

import com.openlattice.authorization.AclKey;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

/**
 * @author Ho Chung Siu
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public interface PermissionsApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE    = "/datastore";
    String CONTROLLER = "/permissions";
    String BASE       = SERVICE + CONTROLLER;

    String EXPLAIN    = "/explain";

    /**
     * Add, removes, or sets the ace for a particular acl key. Successful only if user is the owner of acl key.
     * 
     * @param req The acl key, the principals, and the aces to set for that particular ace key.
     * @return The aces for the acl key, after applying the request changes.
     */
    @PATCH( BASE )
    Void updateAcl( @Body AclData req );

    /**
     * Retrieves the acl for a particular acl key. Only return if user is the owner of acl key.
     * 
     * @param aclKey The acl key.
     * @return The aces for the requested acl key.
     */
    @POST( BASE )
    Acl getAcl( @Body AclKey aclKey );

    /**
     * Retrieves the acl for a particular acl key, with explanation of where the permissions come from. Only return if
     * user is the owner of acl key.
     * 
     * @param aclKey The acl key.
     * @return The aces for the requested acl key, together with the explanation.
     */
    @POST( BASE + EXPLAIN )
    AclExplanation getAclExplanation( @Body AclKey aclKey );

}
