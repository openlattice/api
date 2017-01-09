package com.dataloom.edm.requests;

import java.util.EnumSet;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.Action;
import com.dataloom.authorization.Permission;
import com.dataloom.authorization.Principal;
import com.dataloom.authorization.requests.EntitySetAclRequest;
import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyTypeInEntitySetAclRequest extends EntitySetAclRequest {

    // TODO: Current purpose of requestId is to give frontend UUID of the request, for deletion of PermissionsRequest.
    // Therefore, user should not set this field, and it wouldn't be saved anyway.
    // In long run, perhaps should refactor this to AclRequest, if we actually need UUID for other requests.
    @JsonProperty( SerializationConstants.REQUEST_ID )
    protected UUID              requestId;

    @JsonProperty( SerializationConstants.PROPERTY_FIELD )
    protected FullQualifiedName propertyTypeFqn;

    @JsonProperty( SerializationConstants.TIMESTAMP )
    protected String            timestamp;

    public UUID getRequestId() {
        return requestId;
    }

    public FullQualifiedName getPropertyType() {
        return propertyTypeFqn;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public PropertyTypeInEntitySetAclRequest setPrincipal( Principal principal ) {
        this.principal = principal;
        return this;
    }

    @Override
    public PropertyTypeInEntitySetAclRequest setAction( Action action ) {
        this.action = action;
        return this;
    }

    @Override
    public PropertyTypeInEntitySetAclRequest setPermissions( EnumSet<Permission> permissions ) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public PropertyTypeInEntitySetAclRequest setName( String entitySetName ) {
        this.entitySetName = entitySetName;
        return this;
    }

    public PropertyTypeInEntitySetAclRequest setRequestId( UUID requestId ) {
        this.requestId = requestId;
        return this;
    }

    public PropertyTypeInEntitySetAclRequest setPropertyType( FullQualifiedName propertyTypeFqn ) {
        this.propertyTypeFqn = propertyTypeFqn;
        return this;
    }

    public PropertyTypeInEntitySetAclRequest setTimestamp( String timestamp ) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ( requestId != null ? requestId.hashCode() : 0 );
        result = 31 * result + ( propertyTypeFqn != null ? propertyTypeFqn.hashCode() : 0 );
        result = 31 * result + ( timestamp != null ? timestamp.hashCode() : 0 );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null || getClass() != obj.getClass() )
            return false;
        if ( !super.equals( obj ) )
            return false;

        PropertyTypeInEntitySetAclRequest that = (PropertyTypeInEntitySetAclRequest) obj;

        if ( requestId != null ? !requestId.equals( that.requestId ) : that.requestId != null )
            return false;
        if ( timestamp != null ? !timestamp.equals( that.timestamp ) : that.timestamp != null )
            return false;
        return propertyTypeFqn != null ? propertyTypeFqn.equals( that.propertyTypeFqn ) : that.propertyTypeFqn == null;
    }

    @Override
    public String toString() {
        return "PropertyTypeInEntitySetAclRequest [requestId=" + requestId + ", propertyTypeFqn=" + propertyTypeFqn
                + ", timestamp=" + timestamp + ", entitySetName=" + entitySetName + ", principal=" + principal
                + ", action=" + action + ", permissions=" + permissions + "]";
    }

    @JsonCreator
    public PropertyTypeInEntitySetAclRequest createRequest(
            @JsonProperty( SerializationConstants.PRINCIPAL ) Principal principal,
            @JsonProperty( SerializationConstants.ACTION ) Action action,
            @JsonProperty( SerializationConstants.NAME_FIELD ) String entitySetName,
            @JsonProperty( SerializationConstants.PROPERTY_FIELD ) FullQualifiedName propertyTypeFqn,
            @JsonProperty( SerializationConstants.PERMISSIONS ) EnumSet<Permission> permissions,
            @JsonProperty( SerializationConstants.TIMESTAMP ) String timestamp,
            @JsonProperty( SerializationConstants.REQUEST_ID ) UUID requestId ) {
        return new PropertyTypeInEntitySetAclRequest().setPrincipal( principal ).setAction( action )
                .setName( entitySetName )
                .setPropertyType( propertyTypeFqn ).setPermissions( permissions ).setTimestamp( timestamp )
                .setRequestId( requestId );
    }

}
