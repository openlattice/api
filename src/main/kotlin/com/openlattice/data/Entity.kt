package com.openlattice.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.IdConstants.*
import com.openlattice.client.serialization.SerializationConstants
import org.apache.commons.lang3.StringUtils
import java.time.OffsetDateTime
import java.util.*

/**
 * This class is used as the data representation for both linked and regular data.
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
data class Entity(
        val id: UUID,
        val properties: MutableMap<UUID, MutableSet<Property>>,
        val entityMetadata: EntityMetadata = EntityMetadata(
                Optional.ofNullable(properties.getValue(VERSION_ID.id).firstOrNull() as Long?),
                Optional.ofNullable(getVersions(properties[VERSIONS_ID.id])),
                Optional.ofNullable(getLastWrite(properties.getValue(LAST_WRITE_ID.id).first() as String?))
        )
) : MutableMap<UUID, MutableSet<Property>> by properties {
    constructor(
            properties: MutableMap<UUID, MutableSet<Property>>,
            entityMetadata: EntityMetadata = EntityMetadata()
    ) : this(UUID.fromString(properties.getValue(ID_ID.id).first() as String), properties, entityMetadata)
}

/**
 * So in order to represent both linked entities and regular entities we need to identify what entity sets
 * each particular value came from
 *
 * { property-type-id: { value: {} , entity_set_id
 */

data class EntityMetadata(
        @JsonProperty(SerializationConstants.VERSION) val version: Optional<Long> = Optional.empty(),
        @JsonProperty(SerializationConstants.VERSIONS) val versions: Optional<LongArray> = Optional.empty(),
        @JsonProperty(SerializationConstants.LAST_WRITE) val lastWrite: Optional<OffsetDateTime> = Optional.empty()
)

private fun getVersions(properties: Set<Property>?): LongArray? = properties?.map { it.value as Long }?.toLongArray()
private fun getLastWrite(lastWriteString: String?): OffsetDateTime? = if (StringUtils.isBlank(lastWriteString)) {
    null
} else {
    OffsetDateTime.parse(lastWriteString)
}

