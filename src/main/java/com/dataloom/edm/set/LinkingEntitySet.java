/*
 * Copyright (C) 2017. Kryptnostic, Inc (dba Loom)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * You can contact the owner of the copyright at support@thedataloom.com
 */

package com.dataloom.edm.set;

import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.edm.EntitySet;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class LinkingEntitySet {
    private final EntitySet            entitySet;
    private final Set<Map<UUID, UUID>> linkingProperties;

    @JsonCreator
    public LinkingEntitySet(
            @JsonProperty( SerializationConstants.LINKING_PROPERTIES_FIELD ) EntitySet entitySet,
            @JsonProperty( SerializationConstants.LINKING_PROPERTIES_FIELD ) Set<Map<UUID, UUID>> linkingProperties ) {
        this.entitySet = entitySet;
        this.linkingProperties = linkingProperties;
    }

    @JsonProperty( SerializationConstants.LINKING_PROPERTIES_FIELD )
    public EntitySet getEntitySet() {
        return entitySet;
    }

    @JsonProperty( SerializationConstants.LINKING_PROPERTIES_FIELD )
    public Set<Map<UUID, UUID>> getLinkingProperties() {
        return linkingProperties;
    }
}
