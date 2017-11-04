package com.dataloom.apps;

import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.organization.Organization;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.UUID;

public class AppConfig {

    private final Organization    organization;
    private final Map<String, UUID> config;

    @JsonCreator
    public AppConfig(
            @JsonProperty( SerializationConstants.ORGANIZATION ) Organization organization,
            @JsonProperty( SerializationConstants.CONFIG ) Map<String, UUID> config ) {
        this.organization = organization;
        this.config = config;
    }

    @JsonProperty( SerializationConstants.ORGANIZATION )
    public Organization getOrganization() {
        return organization;
    }

    @JsonProperty( SerializationConstants.CONFIG )
    public Map<String, UUID> getConfig() {
        return config;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        AppConfig appConfig = (AppConfig) o;

        if ( !organization.equals( appConfig.organization ) )
            return false;
        return config.equals( appConfig.config );
    }

    @Override public int hashCode() {
        int result = organization.hashCode();
        result = 31 * result + config.hashCode();
        return result;
    }
}
