package com.openlattice.postgres

import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.authorization.Permission
import com.openlattice.client.serialization.SerializationConstants
import java.util.*

//TODO link to pg_policies

data class RowSecurityPolicy(
        @JsonProperty(SerializationConstants.NAME) val policyName: String,
        @JsonProperty(SerializationConstants.ORGANIZATION_ID) val orgId: UUID,
        @JsonProperty(SerializationConstants.TABLE_ID) val tableId: UUID,
        @JsonProperty(SerializationConstants.PERMISSIONS) val permissions: Set<Permission>,
        @JsonProperty(SerializationConstants.ROLES) val users: Set<String>,
        @JsonProperty(SerializationConstants.FILTER) val readFilter: String,
        @JsonProperty(SerializationConstants.CHECK) val writeCheck: String
)