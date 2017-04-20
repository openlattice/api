package com.dataloom.data.requests;

import java.util.Set;
import java.util.UUID;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BulkDataCreation {
    private Set<UUID>       tickets;
    private Set<Entity>     entities;
    private Set<Association> associations;

    @JsonCreator
    public BulkDataCreation(
            @JsonProperty( SerializationConstants.SYNC_TICKETS ) Set<UUID> tickets,
            @JsonProperty( SerializationConstants.ENTITIES ) Set<Entity> entities,
            @JsonProperty( SerializationConstants.ASSOCIATIONS ) Set<Association> associations ) {
        this.tickets = tickets;
        this.entities = entities;
        this.associations = associations;
    }

    @JsonProperty( SerializationConstants.SYNC_TICKETS )
    public Set<UUID> getTickets() {
        return tickets;
    }

    @JsonProperty( SerializationConstants.ENTITIES )
    public Set<Entity> getEntities() {
        return entities;
    }

    @JsonProperty( SerializationConstants.ASSOCIATIONS )
    public Set<Association> getAssociations() {
        return associations;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( associations == null ) ? 0 : associations.hashCode() );
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
        if ( associations == null ) {
            if ( other.associations != null ) return false;
        } else if ( !associations.equals( other.associations ) ) return false;
        if ( entities == null ) {
            if ( other.entities != null ) return false;
        } else if ( !entities.equals( other.entities ) ) return false;
        if ( tickets == null ) {
            if ( other.tickets != null ) return false;
        } else if ( !tickets.equals( other.tickets ) ) return false;
        return true;
    }

}
