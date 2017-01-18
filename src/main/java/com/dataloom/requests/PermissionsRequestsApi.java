package com.dataloom.requests;

import java.util.List;
import java.util.UUID;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PermissionsRequestsApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE    = "/datastore";
    String CONTROLLER = "/requests";
    String BASE       = SERVICE + CONTROLLER;

    /*
     * These are the actual components after {SERVICE}/{CONTROLLER}/
     */
    String ADMIN       = "admin";
    String UNRESOLVED  = "unresolved";
    String RESOLVED    = "resolved";

    /**
     * Update/Insert a Permission Request. For one user and one securable object List &lt;UUID &gt; , there should only be one unresolved request at any given time. 
     * @param req 
     * <ul>
     *   <li>aclRoot is the root of your permissions request. 
     *     <ul>
     *       <li>If you are requesting a nested object, the root is the truncated List &lt; UUID &gt; without the last element.</li>
     *       <li>If you are requesting a standalone object, the root is the List &lt; UUID &gt; itself.</li>
     *     </ul>
     *   </li>
     *   <li>permissions is a map that specifies the children that you are requesting access to, as well as the permissions you are requesting.
     *     <ul>
     *       <li>If you are requesting a nested object, a child is the last element in the full List &lt; UUID &gt;. The permissions field is then a Map &lt; Child, Set &lt; Permission &gt;&gt;</li>
     *       <li>If you are requesting a standalone object, a child is null. In this case, <b>one should put UUID of aclRoot as the key of the permissions map.</b> </li>
     *     </ul>
     *   </li>
     * </ul>
     * @return
     */
    @PUT( BASE )
    Void upsertRequest( @Body AclRootRequestDetailsPair req );

    /**
     * Get the unresolved request for the current user and the specified securable object. If no such request exist, a 404 should be returned.
     * @param aclRoot
     * @return
     */
    @POST( BASE + "/" + UNRESOLVED )
    PermissionsRequest getUnresolvedRequestOfUser( @Body List<UUID> aclRoot );

    /**
     * Get all resolved requests for the current user and the specified securable object.
     * @param aclRoot
     * @return
     */
    @POST( BASE + "/" + RESOLVED )
    Iterable<PermissionsRequest> getResolvedRequestsOfUser( @Body List<UUID> aclRoot );

    /**
     * Allow owner of a securable object to change the status of an UNRESOLVED permissions request. This allows them to approve/decline a request.
     * @param req Only aclRoot, user, and status has to be passed in.
     * @return
     */
    @POST( BASE + "/" + ADMIN )
    Void updateUnresolvedRequestStatus( @Body PermissionsRequest req );

    /**
     * Allow owner of a securable object to retrieve all unresolved requests.
     * @param req Both aclRoot and status can be empty. If aclRoot is empty, all authorized objects of user would be fetched. If status is empty, all RequestStatus would be fetched.
     * @return
     */
    @POST( BASE + "/" + ADMIN + "/" + UNRESOLVED )
    Iterable<PermissionsRequest> getAllUnresolvedRequestsOfAdmin( @Body AclRootStatusPair req );

}
