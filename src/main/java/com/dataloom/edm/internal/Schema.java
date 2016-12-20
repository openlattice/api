package com.dataloom.edm.internal;

import java.util.Set;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;

/**
 * This class roughly corresponds to {@link CsdlSchema}
 * 
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 * 
 */
public class Schema {
    private final FullQualifiedName                fqn;

    // These columns aren't stored in cassandra
    private final transient Set<FullQualifiedName> propertyTypes;
    private final transient Set<FullQualifiedName> entityTypes;

    @JsonCreator
    public Schema(
            @JsonProperty( SerializationConstants.FQN ) FullQualifiedName fqn,
            @JsonProperty( SerializationConstants.ENTITY_TYPES ) Set<FullQualifiedName> entityTypes,
            @JsonProperty( SerializationConstants.PROPERTY_TYPES ) Set<FullQualifiedName> propertyTypes ) {
        this.fqn = Preconditions.checkNotNull( fqn );
        this.entityTypes = Preconditions.checkNotNull( entityTypes );
        this.propertyTypes = Preconditions.checkNotNull( propertyTypes );
    }

    @JsonProperty( SerializationConstants.FQN )
    public FullQualifiedName getFqn() {
        return fqn;
    }

    @JsonProperty( SerializationConstants.ENTITY_TYPES )
    public Set<FullQualifiedName> getEntityTypes() {
        return entityTypes;
    }

    @JsonProperty( SerializationConstants.PROPERTY_TYPES )
    public Set<FullQualifiedName> getPropertyTypes() {
        return propertyTypes;
    }

    @Override
    public String toString() {
        return "Schema [fqn=" + fqn + ", propertyTypes=" + propertyTypes + ", entityTypes=" + entityTypes + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( entityTypes == null ) ? 0 : entityTypes.hashCode() );
        result = prime * result + ( ( fqn == null ) ? 0 : fqn.hashCode() );
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
        if ( !( obj instanceof Schema ) ) {
            return false;
        }
        Schema other = (Schema) obj;
        if ( entityTypes == null ) {
            if ( other.entityTypes != null ) {
                return false;
            }
        } else if ( !entityTypes.equals( other.entityTypes ) ) {
            return false;
        }
        if ( fqn == null ) {
            if ( other.fqn != null ) {
                return false;
            }
        } else if ( !fqn.equals( other.fqn ) ) {
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

}
