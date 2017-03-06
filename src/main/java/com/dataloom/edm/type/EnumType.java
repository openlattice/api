package com.dataloom.edm.type;

import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public class EnumType extends PropertyType {

    private final LinkedHashSet<?> members;
    private final boolean          flags;
    
    @JsonCreator
    public EnumType(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) FullQualifiedName fqn,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.SCHEMAS ) Set<FullQualifiedName> schemas,
            @JsonProperty( SerializationConstants.DATATYPE_FIELD ) EdmPrimitiveTypeKind datatype,
            @JsonProperty( SerializationConstants.MEMBERS_FIELD ) LinkedHashSet<?> members,
            @JsonProperty( SerializationConstants.FLAGS_FIELD ) boolean flags,
            @JsonProperty( SerializationConstants.PII_FIELD ) Optional<Boolean> piiField,
            @JsonProperty( SerializationConstants.ANALYZER ) Optional<Analyzer> analyzer ) {
        super(
                id,
                fqn,
                title,
                description,
                schemas,
                datatype,
                piiField,
                analyzer );
        this.members = Preconditions.checkNotNull( members, "Members cannot be null" );
        this.flags = flags;
    }

    public Set<?> getMembers() {
        return members;
    }

    public boolean isFlags() {
        return flags;
    }

}
