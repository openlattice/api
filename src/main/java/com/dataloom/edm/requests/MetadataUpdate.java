package com.dataloom.edm.requests;

import java.util.Set;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * Used for updating metadata of property type, entity type, or entity set. Non-existent fields for the specific object
 * would be ignored.
 * 
 * @author Ho Chung Siu
 *
 */
public class MetadataUpdate {
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
