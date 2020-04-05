package com.openlattice.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.client.serialization.SerializationConstants
import java.util.*

/**
 * This class is used for issuing version specific updates.
 *
 * @param entitySetId The entity set id to update.
 * @param entityKeyId The entity key id to update.
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
data class EntityDataKey(
        @JsonProperty(SerializationConstants.ENTITY_SET_ID) val entitySetId: UUID,
        @JsonProperty(SerializationConstants.ENTITY_KEY_ID) val entityKeyId: UUID
) : Comparable<EntityDataKey> {
    override fun compareTo(o: EntityDataKey): Int {
        return if (entitySetId != o.entitySetId) {
            entitySetId.compareTo(o.entitySetId)
        } else entityKeyId.compareTo(o.entityKeyId)
    }
}