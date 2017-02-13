package com.dataloom.datasource;

import com.dataloom.authorization.securable.AbstractSecurableObject;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

import java.util.Set;
import java.util.UUID;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class LoomDatasource extends AbstractSecurableObject {
    private final Set<UUID> entitySetIds;

    public LoomDatasource(
            UUID id,
            String title,
            Optional<String> description,
            @JsonProperty( SerializationConstants.ENTITY_SET_IDS ) Set<UUID> entitySetIds ) {
        this( Optional.of( id ), title, description, entitySetIds );
    }

    @JsonCreator
    public LoomDatasource(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.ENTITY_SET_IDS ) Set<UUID> entitySetIds ) {
        super( id, title, description );
        this.entitySetIds = entitySetIds;
    }

    @JsonProperty( SerializationConstants.ENTITY_SET_IDS )
    public Set<UUID> getEntitySetIds() {
        return entitySetIds;
    }

    @Override
    @JsonIgnore
    public SecurableObjectType getCategory() {
        return SecurableObjectType.Datasource;
    }
}
