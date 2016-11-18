package com.dataloom.edm.internal;

import java.util.EnumSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.requests.EntitySetAclRequest;
import com.dataloom.authorization.requests.Permission;
import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;

public class EntitySetWithPermissions extends EntitySet {
    private EnumSet<Permission> permissions;
    private Boolean isOwner;

    public EnumSet<Permission> getPermissions() {
        return permissions;
    }
    
    public Boolean getIsOwner() {
        return isOwner;
    }

    public EntitySetWithPermissions setPermissions( EnumSet<Permission> permissions ) {
        this.permissions = permissions;
        return this;
    }

    public EntitySetWithPermissions setIsOwner( boolean isOwner ) {
        this.isOwner = isOwner;
        return this;
    }
    
    @Override
    public EntitySetWithPermissions setName( String name ) {
        this.name = name;
        return this;
    }
    
    @Override
    public EntitySetWithPermissions setTitle( String title ) {
        this.title = title;
        return this;
    }
    
    @Override
    public EntitySetWithPermissions setTypename( String typename ) {
        this.typename = typename;
        return this;
    }
    
    @Override
    public EntitySetWithPermissions setType( FullQualifiedName type ) {
        this.type = type;
        return this;
    }
    
    public EntitySetWithPermissions fromEntitySet( EntitySet entitySet ) {
        this.name = entitySet.name;
        this.title = entitySet.title;
        this.typename = entitySet.typename;
        this.type = entitySet.type;
        return this;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ( ( permissions == null ) ? 0 : permissions.hashCode() );
        result = 31 * result + ( ( isOwner == null ) ? 0 : isOwner.hashCode() );
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

        EntitySetWithPermissions that = (EntitySetWithPermissions) obj;

        if ( isOwner != null ? !isOwner.equals( that.isOwner ) : that.isOwner != null )
            return false;
        return permissions != null ? permissions.equals( that.permissions ) : that.permissions == null;
    }

    @Override
    public String toString() {
        return "EntitySetWithPermissions [permissions=" + permissions + ", isOwner=" + isOwner + ", typename="
                + typename + ", name=" + name + ", title=" + title + ", type=" + type + "]";
    }

    @JsonCreator
    public static EntitySetWithPermissions newEntitySet(
            @JsonProperty( SerializationConstants.TYPE_FIELD) FullQualifiedName type,
            @JsonProperty( SerializationConstants.NAME_FIELD) String name,     
            @JsonProperty( SerializationConstants.TITLE_FIELD) String title,
            @JsonProperty( SerializationConstants.PERMISSIONS) EnumSet<Permission> permissions,
            @JsonProperty( SerializationConstants.IS_OWNER) Boolean isOwner ) {
        return new EntitySetWithPermissions()
                .setType( type )
                .setName( name )
                .setTitle( title )
                .setPermissions( permissions )
                .setIsOwner( isOwner );
    }
}
