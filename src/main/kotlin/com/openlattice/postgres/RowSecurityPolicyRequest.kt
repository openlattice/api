package com.openlattice.postgres

import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.client.serialization.SerializationConstants
import java.util.*

data class RowSecurityPolicyRequest(
        @JsonProperty(SerializationConstants.NAME) val policyName: Optional<String>,
        @JsonProperty(SerializationConstants.PERMISSIVENESS) val permissiveness: Optional<String>,
        @JsonProperty(SerializationConstants.USER_ID) val userIds: Optional<List<String>>,
        @JsonProperty(SerializationConstants.PERMISSIONS) val privilege: Optional<PostgresPrivileges>,
        @JsonProperty(SerializationConstants.READ_FILTER) val readFilter: Optional<String>,
        @JsonProperty(SerializationConstants.WRITE_FILTER) val writeFilter: Optional<String>
)