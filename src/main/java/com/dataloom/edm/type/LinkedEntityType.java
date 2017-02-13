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

package com.dataloom.edm.type;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class LinkedEntityType extends EntityType {
    private final Set<UUID>            linkedEntityTypes;
    private final Set<Map<UUID, UUID>> linkingProperties;

    @JsonCreator
    public LinkedEntityType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName type,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD )
                    Optional<String> description,
            @JsonProperty( SerializationConstants.SCHEMAS )
                    Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.KEY_FIELD ) Set<UUID> key,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD ) Set<UUID> properties,
            @JsonProperty( SerializationConstants.LINKING_PROPERTIES_FIELD ) Set<Map<UUID, UUID>> linkingProperties ) {
        super( id, type, title, description, schemas, key, properties );
        this.linkingProperties = checkNotNull( linkingProperties );
        linkedEntityTypes = linkingProperties
                .stream()
                .map( Map::keySet )
                .flatMap( Set::stream )
                .collect( Collectors.toSet() );
    }

    public LinkedEntityType(
            UUID id,
            FullQualifiedName type,
            String title,
            Optional<String> description,
            Set<FullQualifiedName> schemas,
            Set<UUID> key,
            Set<UUID> properties,
            Set<Map<UUID, UUID>> linkingProperties ) {
        this( Optional.of( id ), type, title, description, schemas, key, properties, linkingProperties );
    }

    public LinkedEntityType(
            FullQualifiedName type,
            String title,
            String description,
            Set<FullQualifiedName> schemas,
            Set<UUID> key, Set<UUID> properties,
            Set<Map<UUID, UUID>> linkingProperties ) {
        this( Optional.absent(), type, title, Optional.of( description ), schemas, key, properties, linkingProperties );
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( !( o instanceof LinkedEntityType ) )
            return false;
        if ( !super.equals( o ) )
            return false;

        LinkedEntityType that = (LinkedEntityType) o;

        if ( linkedEntityTypes != null ?
                !linkedEntityTypes.equals( that.linkedEntityTypes ) :
                that.linkedEntityTypes != null )
            return false;
        return linkingProperties != null ?
                linkingProperties.equals( that.linkingProperties ) :
                that.linkingProperties == null;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ( linkedEntityTypes != null ? linkedEntityTypes.hashCode() : 0 );
        result = 31 * result + ( linkingProperties != null ? linkingProperties.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        return "LinkedEntityType [linkedEntityTypes=" + linkedEntityTypes + ", linkingProperties=" + linkingProperties
                + ", schemas=" + schemas + ", type=" + type + ", id=" + id + ", title=" + title + ", description="
                + description + "]";
    }

}
