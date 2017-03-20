package com.dataloom.edm.type;

import java.util.Set;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.authorization.securable.AbstractSchemaAssociatedSecurableType;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

public class LinkingType extends AbstractSchemaAssociatedSecurableType {

    private UUID    src;
    private UUID    dest;
    private boolean bidirectional;

    @JsonCreator
    public LinkingType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName type,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.SRC ) UUID src,
            @JsonProperty( SerializationConstants.DEST ) UUID dest,
            @JsonProperty( SerializationConstants.BIDIRECTIONAL ) boolean bidirectional ) {
        super( id, type, title, description, schemas );
        this.src = src;
        this.dest = dest;
        this.bidirectional = bidirectional;
    }

    public LinkingType(
            UUID id,
            FullQualifiedName type,
            String title,
            Optional<String> description,
            Set<FullQualifiedName> schemas,
            UUID src,
            UUID dest,
            boolean bidirectional ) {
        this( Optional.of( id ), type, title, description, schemas, src, dest, bidirectional );
    }
    
    public LinkingType(
            FullQualifiedName type,
            String title,
            String description,
            Set<FullQualifiedName> schemas,
            UUID src,
            UUID dest,
            boolean bidirectional ) {
        this( Optional.absent(), type, title, Optional.of( description ), schemas, src, dest, bidirectional );
    }

    @JsonProperty( SerializationConstants.SRC )
    public UUID getSrc() {
        return src;
    }

    @JsonProperty( SerializationConstants.DEST )
    public UUID getDest() {
        return dest;
    }

    @JsonProperty( SerializationConstants.BIDIRECTIONAL )
    public boolean isBidirectional() {
        return bidirectional;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( bidirectional ? 1231 : 1237 );
        result = prime * result + ( ( dest == null ) ? 0 : dest.hashCode() );
        result = prime * result + ( ( src == null ) ? 0 : src.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( !super.equals( obj ) ) return false;
        if ( getClass() != obj.getClass() ) return false;
        LinkingType other = (LinkingType) obj;
        if ( bidirectional != other.bidirectional ) return false;
        if ( dest == null ) {
            if ( other.dest != null ) return false;
        } else if ( !dest.equals( other.dest ) ) return false;
        if ( src == null ) {
            if ( other.src != null ) return false;
        } else if ( !src.equals( other.src ) ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "LinkingType [src=" + src + ", dest=" + dest + ", bidirectional=" + bidirectional + ", schemas="
                + schemas + ", type=" + type + ", id=" + id + ", title=" + title + ", description=" + description + "]";
    }

    @Override
    public SecurableObjectType getCategory() {
        return SecurableObjectType.LinkingType;
    }

}
