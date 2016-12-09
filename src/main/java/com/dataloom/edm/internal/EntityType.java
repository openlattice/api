package com.dataloom.edm.internal;

import java.util.Set;

import javax.validation.GroupSequence;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.hibernate.validator.constraints.NotEmpty;

import com.dataloom.data.SerializationConstants;
import com.dataloom.edm.validation.ValidateFullQualifiedName;
import com.dataloom.edm.validation.ValidateKeysInProperties;
import com.dataloom.edm.validation.tags.Extended;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;

@Table(
    keyspace = DatastoreConstants.KEYSPACE,
    name = DatastoreConstants.ENTITY_TYPES_TABLE )
@GroupSequence( { EntityType.class, Extended.class } )
@ValidateKeysInProperties(
    groups = Extended.class )
public class EntityType extends TypePK {
    @Column(
        name = "typename" )
    private String                 typename = null;

    @Column(
        name = "key" )
    @NotEmpty
    @Valid
    private Set<@ValidateFullQualifiedName FullQualifiedName> key;

    @Column(
        name = "properties" )
    @NotEmpty
    @Valid
    public Set<@ValidateFullQualifiedName FullQualifiedName>  properties;

    public EntityType setNamespace( String namespace ) {
        this.namespace = namespace;
        return this;
    }

    public EntityType setName( String name ) {
        this.name = name;
        return this;
    }
    
    @Override
    public EntityType setSchemas( Set<FullQualifiedName> schemas ) {
        this.schemas = schemas;
        return this;
    }

    @JsonIgnore
    public String getTypename() {
        return typename;
    }

    public EntityType setTypename( String typename ) {
        // typename must only be set once
        Preconditions.checkState( StringUtils.isBlank( this.typename ) );
        this.typename = typename;
        return this;
    }

    // TODO: It seems the objects do not allow property types from the different schemas.
    public Set<FullQualifiedName> getKey() {
        return key;
    }

    public EntityType setKey( Set<FullQualifiedName> key ) {
        this.key = key;
        return this;
    }

    public Set<FullQualifiedName> getProperties() {
        return properties;
    }
    
    public EntityType setProperties( Set<FullQualifiedName> properties ) {
        this.properties = properties;
        return this;
    }
    
    public EntityType addProperties( Set<FullQualifiedName> properties ) {
        this.properties.addAll( properties );
        return this;
    }
    
    public EntityType removeProperties( Set<FullQualifiedName> properties ) {
        this.properties.removeAll( properties );
        return this;
    }

    @Override
    public String toString() {
        return "ObjectType [namespace=" + namespace + ", type=" + name + ", typename=" + typename
                + ", key=" + key
                + ", allowed=" + properties
                + ", schemas=" + schemas + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( key == null ) ? 0 : key.hashCode() );
        result = prime * result + ( ( namespace == null ) ? 0 : namespace.hashCode() );
        result = prime * result + ( ( properties == null ) ? 0 : properties.hashCode() );
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        result = prime * result + ( ( typename == null ) ? 0 : typename.hashCode() );
        result = prime * result + ( ( schemas == null ) ? 0 : schemas.hashCode() );
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
        if ( !( obj instanceof EntityType ) ) {
            return false;
        }
        EntityType other = (EntityType) obj;
        if ( key == null ) {
            if ( other.key != null ) {
                return false;
            }
        } else if ( !key.equals( other.key ) ) {
            return false;
        }
        if ( namespace == null ) {
            if ( other.namespace != null ) {
                return false;
            }
        } else if ( !namespace.equals( other.namespace ) ) {
            return false;
        }
        if ( properties == null ) {
            if ( other.properties != null ) {
                return false;
            }
        } else if ( !properties.equals( other.properties ) ) {
            return false;
        }
        if ( name == null ) {
            if ( other.name != null ) {
                return false;
            }
        } else if ( !name.equals( other.name ) ) {
            return false;
        }
        if ( typename == null ) {
            if ( other.typename != null ) {
                return false;
            }
        } else if ( !typename.equals( other.typename ) ) {
            return false;
        }
        if ( schemas == null ) {
            if ( other.schemas != null ) {
                return false;
            }
        } else if ( !schemas.equals( other.schemas ) ) {
            return false;
        }
        return true;
    }

    @JsonCreator
    public static EntityType newEntityType(
            @JsonProperty( SerializationConstants.NAMESPACE_FIELD ) String namespace,
            @JsonProperty( SerializationConstants.NAME_FIELD ) String name,
            @JsonProperty( SerializationConstants.KEY_FIELD ) Set<FullQualifiedName> key,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Set<FullQualifiedName> properties,
            @JsonProperty( SerializationConstants.SCHEMAS) Optional<Set<FullQualifiedName>> schemas) {
        return new EntityType()
                .setNamespace( namespace )
                .setName( name )
                .setTypename( null )
                .setProperties( properties )
                .setKey( key )
                .setSchemas( schemas.or( ImmutableSet.of() ) );
    }
}
