package com.dataloom.apps;

import com.dataloom.authorization.securable.AbstractSecurableObject;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

public class App extends AbstractSecurableObject {

    private String              name;
    private final LinkedHashSet<UUID> appTypeIds;

    @JsonCreator
    public App(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.NAME_FIELD ) String name,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.APP_TYPE_IDS_FIELD ) LinkedHashSet<UUID> appTypeIds ) {
        super( id, title, description );
        checkArgument( StringUtils.isNotBlank( name ), "App name cannot be blank." );
        this.name = name;
        this.appTypeIds = appTypeIds;
    }

    public App( UUID id, String name, String title, Optional<String> description, LinkedHashSet<UUID> configTypeIds ) {
        this( Optional.of( id ), name, title, description, configTypeIds );
    }

    public App( String name, String title, Optional<String> description, LinkedHashSet<UUID> configTypeIds ) {
        this( Optional.absent(), name, title, description, configTypeIds );
    }

    @JsonProperty( SerializationConstants.NAME_FIELD )
    public String getName() {
        return name;
    }

    @Override public SecurableObjectType getCategory() {
        return SecurableObjectType.App;
    }

    @JsonProperty( SerializationConstants.APP_TYPE_IDS_FIELD )
    public LinkedHashSet<UUID> getAppTypeIds() {
        return appTypeIds;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void addAppTypeIds( Set<UUID> appTypeIds ) {
        appTypeIds.addAll( appTypeIds );
    }

    public void removeAppTypeIds( Set<UUID> appTypeIds ) {
        appTypeIds.removeAll( appTypeIds );
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        if ( !super.equals( o ) )
            return false;

        App app = (App) o;

        if ( !name.equals( app.name ) )
            return false;
        return appTypeIds.equals( app.appTypeIds );
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + appTypeIds.hashCode();
        return result;
    }

    @Override public String toString() {
        return "App{" +
                "name='" + name + '\'' +
                ", appTypeIds=" + appTypeIds +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
