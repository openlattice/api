package com.openlattice.data.storage

import com.fasterxml.jackson.annotation.JsonTypeInfo

const val CLASS = "@class"

/**
 *
 * Interface that allows abstract creating an entity loader and entity writer.
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = CLASS)
interface StorageConfiguration {
//    val storageType: StorageType
//    val
}

