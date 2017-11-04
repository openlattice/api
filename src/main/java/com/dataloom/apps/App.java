package com.dataloom.apps;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

import java.util.LinkedHashSet;
import java.util.UUID;

public class App {

    private final UUID                id;
    private final String              name;
    private final String              title;
    private final String              description;
    private final LinkedHashSet<UUID> appTypeIds;

    @JsonCreator
    public App(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.NAME_FIELD ) String name,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) String description,
            @JsonProperty( SerializationConstants.APP_TYPE_IDS_FIELD ) LinkedHashSet<UUID> appTypeIds ) {
        this.id = id.or( UUID.randomUUID() );
        this.name = name;
        this.title = title;
        this.description = description;
        this.appTypeIds = appTypeIds;
    }

    public App( UUID id, String name, String title, String description, LinkedHashSet<UUID> configTypeIds ) {
        this( Optional.of( id ), name, title, description, configTypeIds );
    }

    public App( String name, String title, String description, LinkedHashSet<UUID> configTypeIds ) {
        this( Optional.absent(), name, title, description, configTypeIds );
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public UUID getId() {
        return id;
    }

    @JsonProperty( SerializationConstants.NAME_FIELD )
    public String getName() {
        return name;
    }

    @JsonProperty( SerializationConstants.TITLE_FIELD )
    public String getTitle() {
        return title;
    }

    @JsonProperty( SerializationConstants.DESCRIPTION_FIELD )
    public String getDescription() {
        return description;
    }

    @JsonProperty( SerializationConstants.APP_TYPE_IDS_FIELD )
    public LinkedHashSet<UUID> getAppTypeIds() {
        return appTypeIds;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        App app = (App) o;

        if ( !id.equals( app.id ) )
            return false;
        if ( !name.equals( app.name ) )
            return false;
        if ( !title.equals( app.title ) )
            return false;
        if ( !description.equals( app.description ) )
            return false;
        return appTypeIds.equals( app.appTypeIds );
    }

    @Override public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + appTypeIds.hashCode();
        return result;
    }
}
