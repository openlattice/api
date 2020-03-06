package com.openlattice.postgres

import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.client.serialization.SerializationConstants

data class RowSecurityPolicy(
        @JsonProperty(SerializationConstants.PERMISSIVENESS) val permissiveness: String,
        @JsonProperty(SerializationConstants.USER_ID) val userIds: List<String>,
        @JsonProperty(SerializationConstants.PERMISSIONS) val privilege: PostgresPrivileges,
        @JsonProperty(SerializationConstants.READ_FILTER) val readFilter: String,
        @JsonProperty(SerializationConstants.WRITE_FILTER) val writeFilter: String
)