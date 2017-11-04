package com.dataloom.apps;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import java.util.UUID;

public class AppType {

    private final UUID              id;
    private final FullQualifiedName type;
    private final String            title;
    private final String            description;
    private final UUID              entityTypeId;

    @JsonCreator
    public AppType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName type,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) String description,
            @JsonProperty( SerializationConstants.ENTITY_TYPE_ID_FIELD ) UUID entityTypeId ) {
        this.id = id.or( UUID.randomUUID() );
        this.type = type;
        this.title = title;
        this.description = description;
        this.entityTypeId = entityTypeId;
    }

    public AppType( UUID id, FullQualifiedName type, String title, String description, UUID entityTypeId ) {
        this( Optional.of( id ), type, title, description, entityTypeId );
    }

    public AppType( FullQualifiedName type, String title, String description, UUID entityTypeId ) {
        this( Optional.absent(), type, title, description, entityTypeId );
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public UUID getId() {
        return id;
    }

    @JsonProperty( SerializationConstants.TYPE_FIELD )
    public FullQualifiedName getType() {
        return type;
    }

    @JsonProperty( SerializationConstants.TITLE_FIELD )
    public String getTitle() {
        return title;
    }

    @JsonProperty( SerializationConstants.DESCRIPTION_FIELD )
    public String getDescription() {
        return description;
    }

    @JsonProperty( SerializationConstants.ENTITY_TYPE_ID_FIELD )
    public UUID getEntityTypeId() {
        return entityTypeId;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        AppType appType = (AppType) o;

        if ( !id.equals( appType.id ) )
            return false;
        if ( !type.equals( appType.type ) )
            return false;
        if ( !title.equals( appType.title ) )
            return false;
        if ( !description.equals( appType.description ) )
            return false;
        return entityTypeId.equals( appType.entityTypeId );
    }

    @Override public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + entityTypeId.hashCode();
        return result;
    }
}
