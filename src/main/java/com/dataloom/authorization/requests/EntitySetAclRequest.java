package com.dataloom.authorization.requests;

import java.util.EnumSet;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EntitySetAclRequest extends AclRequest {

    @JsonProperty( SerializationConstants.NAME_FIELD )
    protected String entitySetName;

    public String getName() {
        return entitySetName;
    }

    @Override
    public EntitySetAclRequest setPrincipal( Principal principal ) {
        this.principal = principal;
        return this;
    }

    @Override
    public EntitySetAclRequest setAction( Action action ) {
        this.action = action;
        return this;
    }

    @Override
    public EntitySetAclRequest setPermissions( EnumSet<Permission> permissions ) {
        this.permissions = permissions;
        return this;
    }

    public EntitySetAclRequest setName( String entitySetName ) {
        this.entitySetName = entitySetName;
        return this;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ( entitySetName != null ? entitySetName.hashCode() : 0 );
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

        EntitySetAclRequest that = (EntitySetAclRequest) obj;

        return entitySetName != null ? entitySetName.equals( that.entitySetName ) : that.entitySetName == null;
    }

    @Override
    public String toString() {
        return "EntitySetAclRequest [entitySetName=" + entitySetName + ", principal=" + principal + ", action=" + action
                + ", permissions=" + permissions + "]";
    }

    @JsonCreator
    public EntitySetAclRequest createEntitySetAclRequest(
            @JsonProperty( SerializationConstants.PRINCIPAL ) Principal principal,
            @JsonProperty( SerializationConstants.ACTION ) Action action,
            @JsonProperty( SerializationConstants.NAME_FIELD ) String entitySetName,
            @JsonProperty( SerializationConstants.PERMISSIONS ) EnumSet<Permission> permissions ) {
        return new EntitySetAclRequest().setPrincipal( principal ).setAction( action ).setName( entitySetName )
                .setPermissions( permissions );
    }
}
