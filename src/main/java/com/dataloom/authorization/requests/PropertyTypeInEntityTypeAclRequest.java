package com.dataloom.authorization.requests;

import java.util.EnumSet;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyTypeInEntityTypeAclRequest extends EntityTypeAclRequest {
    
    @JsonProperty( SerializationConstants.PROPERTY_FIELD )
    protected FullQualifiedName propertyTypeFqn;

    public FullQualifiedName getPropertyType() {
        return propertyTypeFqn;
    }

    @Override
    public PropertyTypeInEntityTypeAclRequest setPrincipal( Principal principal ) {
        this.principal = principal;
        return this;
    }

    @Override
    public PropertyTypeInEntityTypeAclRequest setAction( Action action ) {
        this.action = action;
        return this;
    }

    @Override
    public PropertyTypeInEntityTypeAclRequest setType( FullQualifiedName entityTypeFqn ) {
        this.entityTypeFqn = entityTypeFqn;
        return this;
    }

    @Override
    public PropertyTypeInEntityTypeAclRequest setPermissions( EnumSet<Permission> permissions ) {
        this.permissions = permissions;
        return this;
    }

    public PropertyTypeInEntityTypeAclRequest setPropertyType( FullQualifiedName propertyTypeFqn ) {
        this.propertyTypeFqn = propertyTypeFqn;
        return this;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ( propertyTypeFqn != null ? propertyTypeFqn.hashCode() : 0 );
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

        PropertyTypeInEntityTypeAclRequest that = (PropertyTypeInEntityTypeAclRequest) obj;

        return propertyTypeFqn != null ? propertyTypeFqn.equals( that.propertyTypeFqn ) : that.propertyTypeFqn == null;
    }

    @Override
    public String toString() {
        return "PropertyTypeInEntityTypeAclRequest [propertyTypeFqn=" + propertyTypeFqn + ", entityTypeFqn="
                + entityTypeFqn + ", principal=" + principal + ", action=" + action + ", permissions=" + permissions
                + "]";
    }

    @JsonCreator
    public PropertyTypeInEntityTypeAclRequest createRequest(
            @JsonProperty( SerializationConstants.PRINCIPAL ) Principal principal,
            @JsonProperty( SerializationConstants.ACTION ) Action action,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName entityTypeFqn,
            @JsonProperty( SerializationConstants.PROPERTY_FIELD ) FullQualifiedName propertyTypeFqn,
            @JsonProperty( SerializationConstants.PERMISSIONS ) EnumSet<Permission> permissions ) {
        return new PropertyTypeInEntityTypeAclRequest().setPrincipal( principal ).setAction( action ).setType( entityTypeFqn )
                .setPropertyType( propertyTypeFqn ).setPermissions( permissions );
    }

}
