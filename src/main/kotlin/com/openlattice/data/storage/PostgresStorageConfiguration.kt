package com.openlattice.data.storage

import com.fasterxml.jackson.annotation.JsonProperty
import com.geekbeast.configuration.postgres.*
import java.util.*

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
data class PostgresStorageConfiguration(
        @JsonProperty("configuration") val configuration: Properties,
        @JsonProperty("citus") val usingCitus: Boolean = false
) : StorageConfiguration {

}