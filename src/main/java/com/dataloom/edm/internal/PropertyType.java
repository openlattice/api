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
public class PropertyType extends AbstractSchemaAssociatedSecurableType {
    protected EdmPrimitiveTypeKind datatype;
    protected boolean              piiField = false;
    private transient int          h      = 0;

    @JsonCreator
    public PropertyType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName fqn,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.DATATYPE_FIELD ) EdmPrimitiveTypeKind datatype,
            @JsonProperty( SerializationConstants.PII_FIELD ) boolean piiField ) {
        super(
                id,
                fqn,
                title,
                description,
                schemas );
        this.datatype = datatype;
        this.piiField = piiField;
    }

    public PropertyType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName fqn,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.DATATYPE_FIELD ) EdmPrimitiveTypeKind datatype ) {
        super(
                id,
                fqn,
                title,
                description,
                schemas );
        this.datatype = datatype;
    }

    public PropertyType(
            UUID id,
            FullQualifiedName fqn,
            String title,
            Optional<String> description,
            Set<FullQualifiedName> schemas,
            EdmPrimitiveTypeKind datatype ) {
        this( Optional.of( id ), fqn, title, description, schemas, datatype );
    }

    public PropertyType(
            FullQualifiedName fqn,
            String title,
            Optional<String> description,
            Set<FullQualifiedName> schemas,
            EdmPrimitiveTypeKind datatype ) {
        this( Optional.absent(), fqn, title, description, schemas, datatype );
    }

    @JsonProperty( SerializationConstants.DATATYPE_FIELD )
    public EdmPrimitiveTypeKind getDatatype() {
        return datatype;
    }

    @JsonProperty( SerializationConstants.PII_FIELD )
    public boolean isPIIfield() {
        return piiField;
    }

    @Override
    public int hashCode() {
        if ( h == 0 ) {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + ( ( datatype == null ) ? 0 : datatype.hashCode() );
            result = prime * result + ( piiField ? 1231 : 1237 );
            h = result;
        }
        return h;
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
        if ( piiField != other.piiField ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PropertyType [datatype=" + datatype + ", schemas=" + schemas + ", type=" + type + ", id=" + id
                + ", title=" + title + ", description=" + description + ", piiField=" + piiField + "]";
    }

    @Override
    public SecurableObjectType getCategory() {
        return SecurableObjectType.PropertyTypeInEntitySet;
    }
}
