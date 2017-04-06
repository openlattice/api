package com.dataloom.data.requests;

import java.util.Set;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BulkDataCreation {
    private Set<UUID>       tickets;
    private Set<Entity>     entities;
    private Set<Connection> connections;

    @JsonCreator
    public BulkDataCreation(
            @JsonProperty( SerializationConstants.SYNC_TICKETS ) Set<UUID> tickets,
            @JsonProperty( SerializationConstants.ENTITIES ) Set<Entity> entities,
            @JsonProperty( SerializationConstants.CONNECTIONS ) Set<Connection> connections ) {
        this.tickets = tickets;
        this.entities = entities;
        this.connections = connections;
    }

    @JsonProperty( SerializationConstants.SYNC_TICKETS )
    public Set<UUID> getTickets() {
        return tickets;
    }

    @JsonProperty( SerializationConstants.ENTITIES )
    public Set<Entity> getEntities() {
        return entities;
    }

    @JsonProperty( SerializationConstants.CONNECTIONS )
    public Set<Connection> getConnections() {
        return connections;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( connections == null ) ? 0 : connections.hashCode() );
        result = prime * result + ( ( entities == null ) ? 0 : entities.hashCode() );
        result = prime * result + ( ( tickets == null ) ? 0 : tickets.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        BulkDataCreation other = (BulkDataCreation) obj;
        if ( connections == null ) {
            if ( other.connections != null ) return false;
        } else if ( !connections.equals( other.connections ) ) return false;
        if ( entities == null ) {
            if ( other.entities != null ) return false;
        } else if ( !entities.equals( other.entities ) ) return false;
        if ( tickets == null ) {
            if ( other.tickets != null ) return false;
        } else if ( !tickets.equals( other.tickets ) ) return false;
        return true;
    }

}
