package com.dataloom.authorization;

import java.util.Map;
import java.util.Set;

import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface NewPermissionsApi {
    String PERMISSIONS                                 = "permissions";
    String ENTITY_SET                                  = "entityset";
    String PROPERTY_TYPE                               = "propertytype";
    String ES_NAME                                     = "esname";
    String PROPERTY_TYPE_NAMESPACE                     = "namespace";
    String PROPERTY_TYPE_NAME                          = "name";

    String PERMISSIONS_FOR_ENTITY_SET                  = PERMISSIONS + "/{" + ES_NAME + "}";
    String PERMISSIONS_FOR_PROPERTY_TYPE_IN_ENTITY_SET = PERMISSIONS_FOR_ENTITY_SET + "/{" + PROPERTY_TYPE_NAMESPACE
            + "}/" + PROPERTY_TYPE_NAME;

    /**
     * Used to load a list of all entity sets that caller can managed via the Permissions API.
     * 
     * @return A map of all administerable entity sets.
     */
    @GET( PERMISSIONS )
    Map<String, AclKeyInfo> getAdministerableEntitySets();

    /**
     * Retrieves the acl for a particular acl key.
     * 
     * @param request The acl key for which to return an acl. Any aces included in the request will be ignored.
     * @return The aces for the requested acl key.
     */
    /**
     * @param request
     * @return
     */
    @POST( PERMISSIONS )
    Set<AclData> getAcl( AclData request );

    /**
     * Add, removes, or sets the acl for a particular acl key.
     * 
     * @param request The acl key and the aces to set for that particular acl key.
     * @return The acl for acl key, after applying the request changes.
     */
    @PATCH( PERMISSIONS )
    Set<AclData> updateAcl( AclData request );
}
