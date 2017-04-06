package com.dataloom.data.requests;

import java.util.Map;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BulkDataCreation {
    private Map<UUID, Entity>     entities;
    private Map<UUID, Connection> connections;

    @JsonCreator
    public BulkDataCreation(
            @JsonProperty( SerializationConstants.ENTITIES ) Map<UUID, Entity> entities,
            @JsonProperty( SerializationConstants.CONNECTIONS ) Map<UUID, Connection> connections ) {
        this.entities = entities;
        this.connections = connections;
    }

    @JsonProperty( SerializationConstants.ENTITIES )
    public Map<UUID, Entity> getEntities() {
        return entities;
    }

    @JsonProperty( SerializationConstants.CONNECTIONS )
    public Map<UUID, Connection> getConnections() {
        return connections;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( entities == null ) ? 0 : entities.hashCode() );
        result = prime * result + ( ( connections == null ) ? 0 : connections.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        BulkDataCreation other = (BulkDataCreation) obj;
        if ( entities == null ) {
            if ( other.entities != null ) return false;
        } else if ( !entities.equals( other.entities ) ) return false;
        if ( connections == null ) {
            if ( other.connections != null ) return false;
        } else if ( !connections.equals( other.connections ) ) return false;
        return true;
    }

}
