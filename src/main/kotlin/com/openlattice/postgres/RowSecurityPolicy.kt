package com.openlattice.postgres

import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.client.serialization.SerializationConstants
import java.util.*

data class RowSecurityPolicy(
        @JsonProperty(SerializationConstants.NAME) val policyName: String,
        @JsonProperty(SerializationConstants.PERMISSIVENESS) val permissiveness: String,
        @JsonProperty(SerializationConstants.USER_ID) val userIds: List<String>,
        @JsonProperty(SerializationConstants.PERMISSIONS) val privilege: PostgresPrivileges,
        @JsonProperty(SerializationConstants.READ_FILTER) val readFilter: Optional<String>,
        @JsonProperty(SerializationConstants.WRITE_FILTER) val writeFilter: Optional<String>
) {
    init {
        check(readFilter.isPresent || writeFilter.isPresent) {
            "Row security policy requires either a read filter or a write filter"
        }
    }
}