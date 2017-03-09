package com.dataloom.edm.type;

import java.util.*;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class EnumType extends PropertyType {
    private static final EnumSet<EdmPrimitiveTypeKind> ALLOWED_UNDERLYING_TYPES = EnumSet.of(
            EdmPrimitiveTypeKind.Byte,
            EdmPrimitiveTypeKind.SByte,
            EdmPrimitiveTypeKind.Int16,
            EdmPrimitiveTypeKind.Int32,
            EdmPrimitiveTypeKind.Int64 );

    private final LinkedHashSet<String> members;
    private final boolean               flags;

    @JsonCreator
    public EnumType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName fqn,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.MEMBERS_FIELD ) LinkedHashSet<String> members,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.DATATYPE_FIELD ) Optional<EdmPrimitiveTypeKind> datatype,
            @JsonProperty( SerializationConstants.FLAGS_FIELD ) boolean flags,
            @JsonProperty( SerializationConstants.PII_FIELD ) Optional<Boolean> piiField,
            @JsonProperty( SerializationConstants.ANALYZER ) Optional<Analyzer> analyzer ) {
        super(
                id,
                fqn,
                title,
                description,
                schemas,
                datatype.or( EdmPrimitiveTypeKind.Int32 ),
                piiField,
                analyzer );
        Preconditions.checkState( ALLOWED_UNDERLYING_TYPES.contains( this.datatype ),
                "%s is not one of %s",
                this.datatype,
                ALLOWED_UNDERLYING_TYPES );
        this.members = Preconditions.checkNotNull( members, "Members cannot be null" );
        this.flags = flags;
    }

    @JsonProperty( SerializationConstants.MEMBERS_FIELD )
    public Set<String> getMembers() {
        return Collections.unmodifiableSet( members );
    }

    @JsonProperty( SerializationConstants.FLAGS_FIELD )
    public boolean isFlags() {
        return flags;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( flags ? 1231 : 1237 );
        result = prime * result + ( ( members == null ) ? 0 : members.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( !super.equals( obj ) ) {
            return false;
        }
        if ( !( obj instanceof EnumType ) ) {
            return false;
        }
        EnumType other = (EnumType) obj;
        if ( flags != other.flags ) {
            return false;
        }
        if ( members == null ) {
            if ( other.members != null ) {
                return false;
            }
        } else if ( !members.equals( other.members ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EnumType [members=" + members + ", flags=" + flags + ", datatype=" + datatype + ", piiField=" + piiField
                + ", analyzer=" + analyzer + ", schemas=" + schemas + ", type=" + type + ", id=" + id + ", title="
                + title + ", description=" + description + "]";
    }

}
