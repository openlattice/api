package com.dataloom.authorization;

import java.util.List;
import java.util.Map;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

public class AclKeyInfo {
    private final List<AclKey>                   aclKey;
    private final Map<FullQualifiedName, AclKey> propertyTypes;

    public AclKeyInfo( List<AclKey> aclKey, Map<FullQualifiedName, AclKey> propertyTypes ) {
        this.aclKey = aclKey;
        this.propertyTypes = propertyTypes;
    }

    public List<AclKey> getAclKey() {
        return aclKey;
    }

    public Map<FullQualifiedName, AclKey> getPropertyTypes() {
        return propertyTypes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( aclKey == null ) ? 0 : aclKey.hashCode() );
        result = prime * result + ( ( propertyTypes == null ) ? 0 : propertyTypes.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof AclKeyInfo ) ) {
            return false;
        }
        AclKeyInfo other = (AclKeyInfo) obj;
        if ( aclKey == null ) {
            if ( other.aclKey != null ) {
                return false;
            }
        } else if ( !aclKey.equals( other.aclKey ) ) {
            return false;
        }
        if ( propertyTypes == null ) {
            if ( other.propertyTypes != null ) {
                return false;
            }
        } else if ( !propertyTypes.equals( other.propertyTypes ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AclKeyInfo [aclKey=" + aclKey + ", propertyTypes=" + propertyTypes + "]";
    }

}
