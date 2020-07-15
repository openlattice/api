package com.openlattice.data

import com.openlattice.IdConstants
import com.openlattice.IdConstants.*
import java.time.OffsetDateTime
import java.util.*

/**
 * This class is used as the data representation for both lin
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
data class Entity(
        val properties: MutableMap<UUID, MutableSet<Any>>
) : MutableMap<UUID, MutableSet<Any>> by properties {
    val id: UUID = UUID.fromString(getValue(ID_ID.id).first() as String)
    val version: Long = getValue(VERSION_ID.id).first() as Long
    val lastWrite: OffsetDateTime = OffsetDateTime.parse(getValue(LAST_WRITE_ID.id).first() as String )
}

/**
 * So in order to represent both linked entities and regular entities we need to identify what entity sets
 * each particular value came from
 *
 * { property-type-id: { value: {} , entity_set_id
 */

