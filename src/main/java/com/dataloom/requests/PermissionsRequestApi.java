package com.dataloom.requests;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import com.dataloom.authorization.AclKeyPathFragment;
import com.dataloom.authorization.Permission;
import com.dataloom.authorization.Principal;

public interface PermissionsRequestApi {
    String PERMISSIONS = "requests";

    Void upsertRequest( List<AclKeyPathFragment> aclRoot, Map<AclKeyPathFragment, EnumSet<Permission>> permissions );
    
    Void updateRequestStatus( List<AclKeyPathFragment> aclRoot, Principal principal, RequestStatus status );

    PermissionsRequest getUnresolvedRequest( List<AclKeyPathFragment> aclRoot, Principal principal );

    Iterable<PermissionsRequest> getAllUnresolvedRequests( EnumSet<RequestStatus> status );

    Iterable<PermissionsRequest> getAllUnresolvedRequests( List<AclKeyPathFragment> aclRoot, EnumSet<RequestStatus> status );

    Iterable<PermissionsRequest> getResolvedRequests( Principal principal, List<AclKeyPathFragment> aclRoot );

}
