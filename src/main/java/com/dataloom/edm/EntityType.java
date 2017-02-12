/*
 * Copyright 2017 Kryptnostic, Inc. (dba Loom)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.dataloom.edm;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import com.dataloom.authorization.securable.AbstractSchemaAssociatedSecurableType;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public class EntityType extends AbstractSchemaAssociatedSecurableType {
    private final Set<UUID>   key;
    private final Set<UUID>   properties;
    private transient int     h                = 0;

    @JsonCreator
    public EntityType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName type,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.KEY_FIELD ) Set<UUID> key,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Set<UUID> properties ) {
        super( id, type, title, description, schemas );
        Preconditions.checkArgument( !key.isEmpty(), "Key properties cannot be empty" );
        this.key = Preconditions.checkNotNull( key, "Entity set key properties cannot be null" );
        this.properties = Sets.newHashSet( Preconditions.checkNotNull( properties, "Entity set properties cannot be null" ) );
    }

    public EntityType(
            UUID id,
            FullQualifiedName type,
            String title,
            Optional<String> description,
            Set<FullQualifiedName> schemas,
            Set<UUID> key,
            Set<UUID> properties ) {
        this( Optional.of( id ), type, title, description, schemas, key, properties );
    }

    public EntityType(
            FullQualifiedName type,
            String title,
            String description,
            Set<FullQualifiedName> schemas,
            Set<UUID> key,
            Set<UUID> properties ) {
        this( Optional.absent(), type, title, Optional.of( description ), schemas, key, properties );
    }

    // TODO: It seems the objects do not allow property types from the different schemas.
    @JsonProperty( SerializationConstants.KEY_FIELD )
    public Set<UUID> getKey() {
        return Collections.unmodifiableSet( key );
    }

    @JsonProperty( SerializationConstants.PROPERTIES_FIELD )
    public Set<UUID> getProperties() {
        return Collections.unmodifiableSet( properties );
    }

    public SecurableObjectType getCategory() {
        return SecurableObjectType.EntityType;
    }

    public void addPropertyTypes( Set<UUID> propertyTypeIds ) {
        properties.addAll( checkNotNull( propertyTypeIds, "Property type ids cannot be null." ) );
    }

    public void removePropertyTypes( Set<UUID> propertyTypeIds ) {
        properties.removeAll( checkNotNull( propertyTypeIds, "Property type ids cannot be null." ) );
    }

    @Override
    public int hashCode() {
        if ( h == 0 ) {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + ( ( key == null ) ? 0 : key.hashCode() );
            result = prime * result + ( ( properties == null ) ? 0 : properties.hashCode() );
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
        if ( properties == null ) {
            if ( other.properties != null ) {
                return false;
            }
        } else if ( !properties.equals( other.properties ) ) {
            return false;
        }
        return true;
    }
}
