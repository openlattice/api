package com.dataloom.edm.internal;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.hibernate.validator.constraints.NotBlank;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Preconditions;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public class TypePK implements Serializable {
    @PartitionKey(
        value = 0 )
    @NotBlank( message = "Namespace cannot be empty." )
    protected String                 namespace;
    @ClusteringColumn(
        value = 0 )
    @NotBlank( message = "Name cannot be empty." )
    protected String                 name;
    
    @Column(
        name = "schemas" )
    protected Set<FullQualifiedName> schemas = Collections.emptySet();
    
    @Transient
    protected UUID id;

    @Transient
    private FullQualifiedName        fqn;

    public String getNamespace() {
        return namespace;
    }

    public TypePK setNamespace( String namespace ) {
        this.namespace = namespace;
        return this;
    }

    public String getName() {
        return name;
    }

    public TypePK setName( String name ) {
        this.name = name;
        return this;
    }

    public Set<FullQualifiedName> getSchemas() {
        // Ho Chung: very artificial - but this is because Cassandra does not distinguish null and empty things right
        // now :/
        if ( schemas != null ) {
            return schemas;
        } else {
            return Collections.emptySet();
        }
    }

    public TypePK setSchemas( Set<FullQualifiedName> schemas ) {
        this.schemas = schemas;
        return this;
    }
    
    public UUID getId() {
        return id;
    }
    
    public TypePK setId( UUID id ) {
        this.id = id;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( fqn == null ) ? 0 : fqn.hashCode() );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        result = prime * result + ( ( namespace == null ) ? 0 : namespace.hashCode() );
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
        if ( !( obj instanceof TypePK ) ) {
            return false;
        }
        TypePK other = (TypePK) obj;
        if ( fqn == null ) {
            if ( other.fqn != null ) {
                return false;
            }
        } else if ( !fqn.equals( other.fqn ) ) {
            return false;
        }
        if ( id == null ) {
            if ( other.id != null ) {
                return false;
            }
        } else if ( !id.equals( other.id ) ) {
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
        if ( schemas == null ) {
            if ( other.schemas != null ) {
                return false;
            }
        } else if ( !schemas.equals( other.schemas ) ) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Transient
    public FullQualifiedName getFullQualifiedName() {
        if ( fqn == null ) {
            Preconditions.checkState( StringUtils.isNotBlank( namespace ), "Namespace must not be blank." );
            Preconditions.checkState( StringUtils.isNotBlank( name ), "Name must not be blank." );
            fqn = new FullQualifiedName( namespace, name );
        }
        return fqn;
    }

}
