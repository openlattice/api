package com.dataloom.edm.internal;

import java.util.Set;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.SecurableObjectType;
import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public class PropertyType extends TypePK {
    private static final long      serialVersionUID = -1215885855868336578L;
    protected EdmPrimitiveTypeKind datatype;

    @JsonCreator
    public PropertyType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.FQN ) FullQualifiedName fqn,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.DATATYPE_FIELD ) EdmPrimitiveTypeKind datatype ) {
        super(
                id,
                fqn,
                schemas );
        this.datatype = datatype;
    }

    public PropertyType(
            FullQualifiedName fqn,
            Set<FullQualifiedName> schemas,
            EdmPrimitiveTypeKind datatype ) {
        this( Optional.absent(), fqn, schemas, datatype );
    }

    public EdmPrimitiveTypeKind getDatatype() {
        return datatype;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( datatype == null ) ? 0 : datatype.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( !super.equals( obj ) ) {
            return false;
        }
        if ( !( obj instanceof PropertyType ) ) {
            return false;
        }
        PropertyType other = (PropertyType) obj;
        if ( datatype != other.datatype ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PropertyType [datatype=" + datatype + ", type=" + type + ", schemas=" + schemas + ", aclKey=" + aclKey
                + "]";
    }

    @Override
    public SecurableObjectType getCategory() {
        return SecurableObjectType.PropertyTypeInEntitySet;
    }
}
