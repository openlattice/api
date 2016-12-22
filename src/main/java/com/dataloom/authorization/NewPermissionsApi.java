package com.dataloom.authorization;

import java.util.Map;

import com.dataloom.authorization.requests.AclResponse;
import com.dataloom.authorization.requests.SecurableObjectRequest;

import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface NewPermissionsApi {
    String PERMISSIONS       = "permissions";
    String ALL_PATH          = "all";
    String DETAILS_BASE_PATH = "details";

    /**
     * Used to load a list of all entity sets that caller can managed via the Permissions API.
     * 
     * @return A map of all administerable entity sets.
     */
    @GET( PERMISSIONS )
    Map<String, AclKeyInfo> getAdministerableEntitySets();

    /**
     * Retrieves the acl of specified user for a particular acl key. If no user is specified, acl of current user is
     * returned. If user is specified, acl is only returned if current user is owner of the acl key.
     * 
     * @param req The securable object and the principal for which to return an acl. Any aces included in the request
     *            will be ignored.
     * @return The aces for the requested acl key.
     */
    @POST( PERMISSIONS )
    AclResponse getAcl( SecurableObjectRequest req );

    /**
     * Retrieves the acl with full details (whenever applicable) for a particular acl key. For example, if acl key
     * corresponds to an entity set, acls for all property types of the entity set would be returned.
     * 
     * @param req The securable object and the principal, where an acl with full details would be returned. Any aces
     *            included in the request will be ignored.
     * @return The aces for the requested acl key.
     */
    @POST( PERMISSIONS + "/" + DETAILS_BASE_PATH )
    AclResponse getDetailedAcl( SecurableObjectRequest req );

    /**
     * Add, removes, or sets the acl for a particular acl key.
     * 
     * @param req The acl key, the principal, and the aces to set for that particular ace key.
     * @return The acl for acl key, after applying the request changes.
     */
    @PATCH( PERMISSIONS )
    Iterable<AclResponse> updateAcl( SecurableObjectRequest req );

    /**
     * Retrieves all acls for a particular acl key.
     * 
     * Only return if user is the owner of acl key.
     * 
     * @param req The securable object for which to return an acl. Any principal and aces included in the request
     *            will be ignored.
     * @return The aces for the requested acl key.
     */
    @POST( PERMISSIONS + "/" + ALL_PATH )
    Iterable<AclResponse> getAllAcl( SecurableObjectRequest req );
}
