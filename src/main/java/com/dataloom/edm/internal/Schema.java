package com.dataloom.edm.internal;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;


/**
 * This class roughly corresponds to {@link CsdlSchema}
 * 
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 * 
 */
public class Schema {
    private UUID                              aclId;
    private String                            namespace;
    private String                            name;
    private Set<FullQualifiedName>            entityTypeFqns;
    private Set<FullQualifiedName>            propertyTypeFqns;

    // These columns aren't stored in cassandra
    private final transient Set<PropertyType> propertyTypes;
    private final transient Set<EntityType>   entityTypes;

    public Schema() {
        this( ImmutableSet.of(), ImmutableSet.of() );
    }

    public Schema( Set<EntityType> entityTypes, Set<PropertyType> propertyTypes ) {
        this.entityTypes = Sets.newHashSet( entityTypes );
        this.propertyTypes = Sets.newHashSet( propertyTypes );

        setEntityTypeFqns( entityTypes.stream()
                .map( EntityType::getType )
                .collect( Collectors.toSet() ) );

        setPropertyTypeFqns( propertyTypes.stream()
                .map( PropertyType::getType )
                .collect( Collectors.toSet() ) );
    }

    @JsonProperty( SerializationConstants.NAMESPACE_FIELD )
    public String getNamespace() {
        return namespace;
    }

    public Schema setNamespace( String namespace ) {
        this.namespace = namespace;
        return this;
    }

    @JsonProperty( SerializationConstants.ACL_ID_FIELD )
    public UUID getAclId() {
        return aclId;
    }

    @JsonProperty( SerializationConstants.NAME_FIELD )
    public String getName() {
        return name;
    }

    public Schema setName( String name ) {
        this.name = name;
        return this;
    }

    public Set<FullQualifiedName> getEntityTypeFqns() {
        return entityTypeFqns;
    }

    public Schema setEntityTypeFqns( Set<FullQualifiedName> entityTypeFqns ) {
        this.entityTypeFqns = entityTypeFqns;
        return this;
    }

    public Set<FullQualifiedName> getPropertyTypeFqns() {
        return propertyTypeFqns;
    }

    public Schema setPropertyTypeFqns( Set<FullQualifiedName> propertyTypeFqns ) {
        this.propertyTypeFqns = propertyTypeFqns;
        return this;
    }

    public Set<EntityType> getEntityTypes() {
        return entityTypes;
    }

    public Set<PropertyType> getPropertyTypes() {
        return propertyTypes;
    }

    public void addEntityTypes( Set<EntityType> entityTypes ) {
        this.entityTypes.addAll( entityTypes );
        // Need to sync entity type names
        entityTypes.forEach( entityType -> entityTypeFqns
                .add( entityType.getType() ) );
    }

    public void addPropertyTypes( Set<PropertyType> propertyTypes ) {
        this.propertyTypes.addAll( propertyTypes );
        // Need to sync property type names
        propertyTypes.forEach( propertyType -> propertyTypeFqns
                .add( propertyType.getType() ) );
    }

    @Override
    public String toString() {
        return "Schema [aclId=" + aclId + ", namespace=" + namespace + ", name=" + name
                + ", entityFqns=" + entityTypeFqns + ", propertyFqns=" + propertyTypeFqns
                + ", entityTypes=" + entityTypes + ", propertyTypes=" + propertyTypes + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( aclId == null ) ? 0 : aclId.hashCode() );
        result = prime * result + ( ( entityTypeFqns == null ) ? 0 : entityTypeFqns.hashCode() );
        result = prime * result + ( ( propertyTypeFqns == null ) ? 0 : propertyTypeFqns.hashCode() );
        result = prime * result + ( ( entityTypes == null ) ? 0 : entityTypes.hashCode() );
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        result = prime * result + ( ( namespace == null ) ? 0 : namespace.hashCode() );
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
        if ( aclId == null ) {
            if ( other.aclId != null ) {
                return false;
            }
        } else if ( !aclId.equals( other.aclId ) ) {
            return false;
        }
        if ( entityTypeFqns == null ) {
            if ( other.entityTypeFqns != null ) {
                return false;
            }
        } else if ( !entityTypeFqns.equals( other.entityTypeFqns ) ) {
            return false;
        }
        if ( entityTypes == null ) {
            if ( other.entityTypes != null ) {
                return false;
            }
        } else if ( !entityTypes.equals( other.entityTypes ) ) {
            return false;
        }
        if ( name == null ) {
            if ( other.name != null ) {
                return false;
            }
        } else if ( !name.equals( other.name ) ) {
            return false;
        }
        if ( namespace == null ) {
            if ( other.namespace != null ) {
                return false;
            }
        } else if ( !namespace.equals( other.namespace ) ) {
            return false;
        }
        if ( propertyTypeFqns == null ) {
            if ( other.propertyTypeFqns != null ) {
                return false;
            }
        } else if ( !propertyTypeFqns.equals( other.propertyTypeFqns ) ) {
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

    @JsonCreator
    public static Schema deserializeSchema(
            Optional<UUID> aclId,
            String namespace,
            String name,
            Optional<Set<FullQualifiedName>> entityTypeNames,
            Optional<Set<EntityType>> entityTypes,
            Optional<Set<FullQualifiedName>> propertyTypeNames,
            Optional<Set<PropertyType>> propertyTypes ) {

        Preconditions.checkArgument(
                ( entityTypes.isPresent() && !entityTypeNames.isPresent() ) || !entityTypes.isPresent() );
        Preconditions.checkArgument(
                ( propertyTypes.isPresent() && !propertyTypeNames.isPresent() ) || !propertyTypes.isPresent() );

        Schema schema = new Schema( entityTypes.or( ImmutableSet.of() ), propertyTypes.or( ImmutableSet.of() ) )
                .setNamespace( namespace )
                .setName( name );

        schema.setEntityTypeFqns( entityTypeNames.or( schema.getEntityTypeFqns() ) );
        schema.setPropertyTypeFqns( propertyTypeNames.or( schema.getPropertyTypeFqns() ) );

        return schema;
    }
}
