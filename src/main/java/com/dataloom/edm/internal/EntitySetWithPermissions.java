package com.dataloom.edm.internal;

import java.util.EnumSet;

import com.dataloom.authorization.requests.Permission;

public class EntitySetWithPermissions {
    private EntitySet           entitySet;
    private final Acl
    private Boolean             isOwner;

    public EntitySet getEntitySet() {
        return entitySet;
    }

    public EnumSet<Permission> getPermissions() {
        return permissions;
    }

    public Boolean getIsOwner() {
        return isOwner;
    }

    public EntitySetWithPermissions setEntitySet( EntitySet entitySet ) {
        this.entitySet = entitySet;
        return this;
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
    public int hashCode() {
        int result = ( ( entitySet == null ) ? 0 : entitySet.hashCode() );
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

        EntitySetWithPermissions that = (EntitySetWithPermissions) obj;

        if ( entitySet != null ? !entitySet.equals( that.entitySet ) : that.entitySet != null )
            return false;
        if ( isOwner != null ? !isOwner.equals( that.isOwner ) : that.isOwner != null )
            return false;
        return permissions != null ? permissions.equals( that.permissions ) : that.permissions == null;
    }

    @Override
    public String toString() {
        return "EntitySetWithPermissions [entitySet=" + entitySet + ", permissions=" + permissions + ", isOwner="
                + isOwner + "]";
    }

}