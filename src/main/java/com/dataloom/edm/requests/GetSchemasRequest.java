package com.dataloom.edm.requests;

import java.util.EnumSet;
import java.util.Set;

import com.dataloom.edm.EdmApi;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Class specifying request options for loading schemas.
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public class GetSchemasRequest {
    public static enum TypeDetails {
        ENTITY_TYPES,
        PROPERTY_TYPES
    }

    private final Optional<String> namespace;
    private final Optional<String> name;
    private final Set<TypeDetails> typeDetails;

    @JsonCreator
    public GetSchemasRequest(
            @JsonProperty( EdmApi.NAMESPACE ) Optional<String> namespace,
            @JsonProperty( EdmApi.NAME ) Optional<String> name,
            @JsonProperty( EdmApi.LOAD_DETAILS ) Optional<Set<TypeDetails>> loadDetails ) {
        Preconditions.checkArgument( (name.isPresent() && namespace.isPresent()) || (!name.isPresent() && !namespace.isPresent()),
                "Namespace must provided if name is present." );
        this.namespace = namespace;
        this.name = name;
        this.typeDetails = loadDetails.or( EnumSet.of( TypeDetails.ENTITY_TYPES, TypeDetails.PROPERTY_TYPES ) );
    }

    @JsonProperty( EdmApi.NAMESPACE )
    public Optional<String> getNamespace() {
        return namespace;
    }

    @JsonProperty( EdmApi.NAME )
    public Optional<String> getName() {
        return name;
    }

    @JsonProperty( EdmApi.LOAD_DETAILS )
    public Set<TypeDetails> getLoadDetails() {
        return typeDetails;
    }

}
