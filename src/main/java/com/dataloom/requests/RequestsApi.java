package com.dataloom.requests;

import retrofit2.http.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public interface RequestsApi {
    String SERVICE    = "/datastore";
    String CONTROLLER = "/requests";
    String BASE       = SERVICE + CONTROLLER;

    /*
     * These are the actual components after {SERVICE}/{CONTROLLER}/
     */

    String STATUS              = "/status";
    String REQUEST_STATUS      = "reqStatus";
    String REQUEST_STATUS_PATH = "/{" + REQUEST_STATUS + "}";

    /**
     * Retrieves the statuses of all permission requests for the current account.
     * <p>
     * The API will stream a potentially very large set of results back to the client
     *
     * @return Permission requests in the state {@link RequestStatus#SUBMITTED } across all objects.
     */
    @GET( BASE )
    Iterable<Status> getMyRequests();

    /**
     * Retrieves the statuses of all outstanding permission requests.
     *
     * @return All permission requests in the state {@link RequestStatus#SUBMITTED }
     */
    @GET( BASE + REQUEST_STATUS_PATH )
    Iterable<Status> getMyRequests( @Path( REQUEST_STATUS ) RequestStatus requestStatus );

    /**
     * Submits a set of permission requests.
     *
     * @param requests Requests to submit
     * @return
     */
    @PUT( BASE )
    Void submit( @Body Set<Request> requests );

    /**
     * Retrieves all status information for a set of AclKeys.
     *
     * @param aclKeys A set of aclKeys for which to retrieve status information.
     * @return For each aclKey ( {@code List<UUID>} ) this will return all statuses in any state across all users for
     * callers who are owners and the caller's statuses in any state for callers who are not owners.
     */
    @POST( BASE )
    Iterable<Status> getStatuses( @Body Set<List<UUID>> aclKeys );

    /**
     * Retrieves the status for all objects in the state specified by {@code requestStatus}
     *
     * @param requestStatus The {@link RequestStatus} to match against.
     * @param requests      A list of acl keys whose status will be checked against
     * @return For each aclKey ( {@code List<UUID>} ) this will return all statuses in the state specified by {@code requestStatus} across all users for
     * callers who are owners and the caller's statuses in the state specified by {@code requestStatus} for callers who are not owners
     */
    @POST( BASE + REQUEST_STATUS_PATH )
    Iterable<Status> getStatuses( @Path( REQUEST_STATUS ) RequestStatus requestStatus, @Body Set<List<UUID>> requests );

    /**
     * Updates a set of permission requests.
     *
     * @param statuses The status updates to attempt to apply.
     * @return 200 OK if request succeeds or 403 if the user doesn't have ownership over all objects he is attempting to udpate.s
     */
    @PATCH( BASE )
    Void updateStatuses( @Body Set<Status> statuses );

}
