package com.dataloom.authorization.requests;

import java.util.EnumSet;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityTypeAclRequest extends AclRequest {

    @JsonProperty( SerializationConstants.TYPE_FIELD )
    protected FullQualifiedName entityTypeFqn;

    public FullQualifiedName getType(){
        return entityTypeFqn;
    }
    
    @Override
    public EntityTypeAclRequest setPrincipal( Principal principal ) {
        this.principal = principal;
        return this;
    }

    @Override
    public EntityTypeAclRequest setAction( Action action ) {
        this.action = action;
        return this;
    }

    @Override
    public EntityTypeAclRequest setPermissions( EnumSet<Permission> permissions ) {
        this.permissions = permissions;
        return this;
    }

    public EntityTypeAclRequest setType( FullQualifiedName entityTypeFqn ) {
        this.entityTypeFqn = entityTypeFqn;
        return this;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        if ( !super.equals( o ) )
            return false;
        
        EntityTypeAclRequest that = (EntityTypeAclRequest) o;

        return entityTypeFqn != null ? entityTypeFqn.equals( that.entityTypeFqn ) : that.entityTypeFqn == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ( entityTypeFqn != null ? entityTypeFqn.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        return "EntityTypeAclRequest [entityTypeFqn=" + entityTypeFqn + ", principal=" + principal + ", action="
                + action + ", permissions=" + permissions + "]";
    }

    @JsonCreator
    public EntityTypeAclRequest createAclRequest(
            @JsonProperty( SerializationConstants.PRINCIPAL ) Principal principal,
            @JsonProperty( SerializationConstants.ACTION ) Action action,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName entityTypeFqn,
            @JsonProperty( SerializationConstants.PERMISSIONS ) EnumSet<Permission> permissions ) {
        return new EntityTypeAclRequest().setPrincipal( principal ).setAction( action ).setType( entityTypeFqn )
                .setPermissions( permissions );
    }

}
