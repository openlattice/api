package com.dataloom.edm.internal;

import java.util.Set;

import javax.validation.constraints.Min;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.SerializationConstants;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

@Table(
    keyspace = DatastoreConstants.KEYSPACE,
    name = DatastoreConstants.PROPERTY_TYPES_TABLE )
public class PropertyType extends PropertyTypeBase {

    @Column(
        name = "typename" )
    protected String typename;

    @Column(
        name = "multiplicity" )
    @Min(0)
    public long      multiplicity;

    @Override
    public PropertyType setNamespace( String namespace ) {
        this.namespace = namespace;
        return this;
    }

    @JsonIgnore
    public String getTypename() {
        return typename;
    }

    @Override
    public PropertyType setName( String name ) {
        this.name = name;
        return this;
    }
    
    @Override
    public PropertyType setSchemas( Set<FullQualifiedName> schemas ) {
        this.schemas = schemas;
        return this;
    }

    @JsonIgnore
    public PropertyType setTypename( String typename ) {
        this.typename = typename;
        return this;
    }

    @Override
    public PropertyType setDatatype( EdmPrimitiveTypeKind datatype ) {
        this.datatype = datatype;
        return this;
    }

    public long getMultiplicity() {
        return multiplicity;
    }

    public PropertyType setMultiplicity( long multiplicity ) {
        this.multiplicity = multiplicity;
        return this;
    }

    @Override
    public String toString() {
        return "PropertyType [typename=" + typename + ", multiplicity=" + multiplicity + ", datatype=" + datatype
                + ", namespace=" + namespace + ", name=" + name 
                + ", schemas=" + schemas + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (int) ( multiplicity ^ ( multiplicity >>> 32 ) );
        result = prime * result + ( ( typename == null ) ? 0 : typename.hashCode() );
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
        if ( multiplicity != other.multiplicity ) {
            return false;
        }
        if ( typename == null ) {
            if ( other.typename != null ) {
                return false;
            }
        } else if ( !typename.equals( other.typename ) ) {
            return false;
        }
        return true;
    }

    @JsonCreator
    public static PropertyType createPropertyType(
            @JsonProperty( SerializationConstants.NAMESPACE_FIELD ) String namespace,
            @JsonProperty( SerializationConstants.NAME_FIELD ) String name,
            @JsonProperty( SerializationConstants.DATATYPE_FIELD ) EdmPrimitiveTypeKind datatype,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) int multiplicity,
            @JsonProperty( SerializationConstants.SCHEMAS) Optional<Set<FullQualifiedName>> schemas) {

        return new PropertyType().setNamespace( namespace ).setName( name ).setDatatype( datatype )
                .setMultiplicity( multiplicity )
                .setSchemas( schemas.or( ImmutableSet.of() ) );
    }

    
}
