/*
 * Copyright (C) 2019. OpenLattice, Inc.
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
 * You can contact the owner of the copyright at support@openlattice.com
 *
 *
 */
package com.openlattice.search.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.client.serialization.SerializationConstants
import java.util.UUID
import java.util.Optional

data class EntityNeighborsFilterBulk(
        @JsonProperty(SerializationConstants.ENTITY_KEY_IDS) val entityKeyIds: Map<UUID, Set<UUID>>,
        @JsonProperty(SerializationConstants.SRC) val srcEntitySetIds: Optional<Set<UUID>>,
        @JsonProperty(SerializationConstants.DST) val dstEntitySetIds: Optional<Set<UUID>>,
        @JsonProperty(SerializationConstants.EDGE) val associationEntitySetIds: Optional<Set<UUID>>
) {
    constructor(
            entityKeyIds: Map<UUID, Set<UUID>>
    ) : this(entityKeyIds, Optional.empty(), Optional.empty(), Optional.empty())

    constructor(
            entitySetId: UUID,
            filter: EntityNeighborsFilter
    ) : this(
            mapOf(entitySetId to filter.entityKeyIds),
            filter.srcEntitySetIds,
            filter.dstEntitySetIds,
            filter.associationEntitySetIds
    )

    init {
        require(entityKeyIds.isNotEmpty()) { "EntityKeyIds cannot be empty in filter." }
        entityKeyIds.forEach { (entitySetId, ids) ->
            require(ids.isNotEmpty()) { "Ids cannot be empty for any entity set. Violating key: $entitySetId" }
        }
    }
}