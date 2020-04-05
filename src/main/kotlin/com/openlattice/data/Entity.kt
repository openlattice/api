package com.openlattice.data

import com.openlattice.IdConstants.ID_ID
import com.openlattice.IdConstants.VERSION_ID
import java.time.OffsetDateTime
import java.util.*

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
data class Entity(
        val properties: MutableMap<UUID, MutableSet<Any>>
) : MutableMap<UUID, MutableSet<Any>> by properties {
    val id: UUID = UUID.fromString(getValue(ID_ID.id).first() as String)
    val version: Long = getValue(VERSION_ID.id).first() as Long
    val lastWrite: OffsetDateTime = OffsetDateTime.parse(getValue(VERSION_ID.id).first() as String )
}
