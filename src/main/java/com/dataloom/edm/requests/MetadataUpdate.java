package com.dataloom.edm.requests;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Used for updating metadata of property type, entity type, or entity set. Non-existent fields for the specific object
 * would be ignored.
 * 
 * @author Ho Chung Siu
 *
 */
public class MetadataUpdate implements Serializable {
    private static final long serialVersionUID = 5732378212397360336L;
    // Common across property type, entity type, entity set
    private Optional<String>            title;
    private Optional<String>            description;
    // Specific to entity set
    private Optional<String>            name;
    private Optional<Set<String>>       contacts;
    // Specific to property type/entity type
    private Optional<FullQualifiedName> type;

    @JsonCreator
    public MetadataUpdate(
            @JsonProperty( SerializationConstants.TITLE_FIELD ) Optional<String> title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.NAME_FIELD ) Optional<String> name,
            @JsonProperty( SerializationConstants.CONTACTS ) Optional<Set<String>> contacts,
            @JsonProperty( SerializationConstants.TYPE_FIELD ) Optional<FullQualifiedName> type ) {
        // WARNING These checks have to be consistent with the same check elsewhere.
        Preconditions.checkArgument( !title.isPresent() || StringUtils.isNotBlank( title.get() ),
                "Title cannot be blank." );
        Preconditions.checkArgument( !name.isPresent() || StringUtils.isNotBlank( name.get() ),
                "Entity set name cannot be blank." );
        Preconditions.checkArgument( !contacts.isPresent() || !contacts.get().isEmpty(), "Contacts cannot be blank." );
        Preconditions.checkArgument( !type.isPresent() || StringUtils.isNotBlank( type.get().getNamespace() ),
                "Namespace of type is missing." );
        Preconditions.checkArgument( !type.isPresent() || StringUtils.isNotBlank( type.get().getName() ),
                "Name of type is missing." );

        this.title = title;
        this.description = description;
        this.name = name;
        this.contacts = contacts;
        this.type = type;
    }

    @JsonProperty( SerializationConstants.TITLE_FIELD )
    public Optional<String> getTitle() {
        return title;
    }

    @JsonProperty( SerializationConstants.DESCRIPTION_FIELD )
    public Optional<String> getDescription() {
        return description;
    }

    @JsonProperty( SerializationConstants.NAME_FIELD )
    public Optional<String> getName() {
        return name;
    }

    @JsonProperty( SerializationConstants.CONTACTS )
    public Optional<Set<String>> getContacts() {
        return contacts;
    }

    @JsonProperty( SerializationConstants.TYPE_FIELD )
    public Optional<FullQualifiedName> getType() {
        return type;
    }

    // Trimming happens before initializing update processors so that irrelevant fields won't get ser/deserialized when
    // processors are serialized.
    public static MetadataUpdate trimToPropertyTypeUpdate( MetadataUpdate update ) {
        return new MetadataUpdate(
                update.getTitle(),
                update.getDescription(),
                Optional.absent(),
                Optional.absent(),
                update.getType() );
    }

    public static MetadataUpdate trimToEntityTypeUpdate( MetadataUpdate update ) {
        return new MetadataUpdate(
                update.getTitle(),
                update.getDescription(),
                Optional.absent(),
                Optional.absent(),
                update.getType() );
    }

    public static MetadataUpdate trimToEntitySetUpdate( MetadataUpdate update ) {
        return new MetadataUpdate(
                update.getTitle(),
                update.getDescription(),
                update.getName(),
                update.getContacts(),
                Optional.absent() );
    }

}
