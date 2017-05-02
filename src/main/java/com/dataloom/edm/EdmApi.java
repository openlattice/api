package com.dataloom.edm;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.dataloom.edm.requests.EdmDetailsSelector;
import com.dataloom.edm.requests.EdmRequest;
import com.dataloom.edm.requests.MetadataUpdate;
import com.dataloom.edm.type.ComplexType;
import com.dataloom.edm.type.EdgeType;
import com.dataloom.edm.type.EntityType;
import com.dataloom.edm.type.EnumType;
import com.dataloom.edm.type.PropertyType;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public interface EdmApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE    = "/datastore";
    String CONTROLLER = "/edm";
    String BASE       = SERVICE + CONTROLLER;

    public static enum FileType {
        json,
        yaml
    }

    /*
     * These are the actual components after {SERVICE}/{CONTROLLER}/
     */
    String ID                         = "id";
    String ENTITY_TYPE_ID             = "entityTypeId";
    String PROPERTY_TYPE_ID           = "propertyTypeId";
    String NAME                       = "name";
    String NAMESPACE                  = "namespace";
    String NAMESPACES                 = "namespaces";
    String ENTITY_SETS                = "entitySets";
    String ENTITY_TYPES               = "entityTypes";
    String PROPERTY_TYPES             = "propertyTypes";
    String SCHEMA                     = "schema";
    String SCHEMAS                    = "schemas";
    String FILE_TYPE                  = "fileType";
    String TOKEN                      = "token";

    // {namespace}/{schema_name}/{class}/{FQN}/{FQN}
    /*
     * /entity/type/{namespace}/{name} /entity/set/{namespace}/{name} /schema/{namespace}/{name}
     * /property/{namespace}/{name}
     */

    String IDS_PATH                   = "/ids";
    String SCHEMA_PATH                = "/schema";
    String ENUM_TYPE_PATH             = "/enum/type";
    String ENTITY_SETS_PATH           = "/entity/set";
    String ENTITY_TYPE_PATH           = "/entity/type";
    String PROPERTY_TYPE_PATH         = "/property/type";
    String COMPLEX_TYPE_PATH          = "/complex/type";
    String ASSOCIATION_TYPE_PATH      = "/association/type";
    String HIERARCHY_PATH             = "/hierarchy";

    String NAMESPACE_PATH             = "/{" + NAMESPACE + "}";
    String NAME_PATH                  = "/{" + NAME + "}";
    String ID_PATH                    = "/{" + ID + "}";
    String ENTITY_TYPE_ID_PATH        = "/{" + ENTITY_TYPE_ID + "}";
    String PROPERTY_TYPE_ID_PATH      = "/{" + PROPERTY_TYPE_ID + "}";

    String SCHEMA_BASE_PATH           = BASE + SCHEMA_PATH;
    String ENTITY_SETS_BASE_PATH      = BASE + ENTITY_SETS_PATH;
    String ENTITY_TYPE_BASE_PATH      = BASE + ENTITY_TYPE_PATH;
    String PROPERTY_TYPE_BASE_PATH    = BASE + PROPERTY_TYPE_PATH;
    String ASSOCIATION_TYPE_BASE_PATH = BASE + ASSOCIATION_TYPE_PATH;

    /**
     * Gets the entity data model, including namespaces, schemas, entity types, and property types. Also returns entity
     * set details for any entity sets the user has permissions for.
     *
     * @return EntityDataModel - The entire entity data model, including namespaces, schemas, entity and property types, and any entity
     * sets the caller has permissions to.
     */
    @GET( BASE )
    EntityDataModel getEntityDataModel();

    /**
     * Gets information for any SecurableObjectType given its type and ID.
     *
     * @param selectors A set containing a given SecurableObjectType, ID, and a set of fields (SecurableObjectType)
     *                  to include in the response.
     * @return EdmDetails - The SecurableObjectType details requested.
     */
    @POST( BASE )
    EdmDetails getEdmDetails( @Body Set<EdmDetailsSelector> selectors );

    /**
     * Gets all property type details from the data model.
     *
     * @return An iterable containing all property types.
     */
    @GET( PROPERTY_TYPE_BASE_PATH )
    Iterable<PropertyType> getPropertyTypes();

    /**
     * Gets property type details for a property with a given ID.
     *
     * @param propertyTypeId ID for a given property type.
     * @return A property type with a given UUID.
     */
    @GET( PROPERTY_TYPE_BASE_PATH + ID_PATH )
    PropertyType getPropertyType( @Path( ID ) UUID propertyTypeId );

    /**
     * Gets property types that have the given namespace.
     *
     * @param namespace Name of the namespace.
     * @return An iterable containing property types in the given namespace.
     */
    @GET( PROPERTY_TYPE_BASE_PATH + "/" + NAMESPACE + NAMESPACE_PATH )
    Iterable<PropertyType> getPropertyTypesInNamespace( @Path( NAMESPACE ) String namespace );

    /**
     * Creates a property type if it doesn't exist. If property type already exists, then no action is taken.
     *
     * @param propertyType The property to create.
     * @return ID for the property created.
     */
    @POST( PROPERTY_TYPE_BASE_PATH )
    UUID createPropertyType( @Body PropertyType propertyType );

    /**
     * Deletes a property with a given ID. If the property is associated with an entity set, then no action is taken.
     *
     * @param propertyTypeId The property ID for the property to delete.
     */
    @DELETE( PROPERTY_TYPE_BASE_PATH + ID_PATH )
    Void deletePropertyType( @Path( ID ) UUID propertyTypeId );

    @GET( BASE + ENUM_TYPE_PATH )
    Iterable<EnumType> getEnumTypes();

    @POST( BASE + ENUM_TYPE_PATH )
    UUID createEnumType( @Body EnumType enumType );

    @GET( BASE + ENUM_TYPE_PATH + ID_PATH )
    EnumType getEnumType( @Path( ID ) UUID enumTypeId );

    @DELETE( BASE + ENUM_TYPE_PATH + ID_PATH )
    Void deleteEnumType( @Path( ID ) UUID enumTypeId );

    @GET( BASE + COMPLEX_TYPE_PATH )
    Iterable<ComplexType> getComplexTypes();

    @POST( BASE + COMPLEX_TYPE_PATH )
    UUID createComplexType( @Body ComplexType complexType );

    @GET( BASE + COMPLEX_TYPE_PATH + ID_PATH )
    ComplexType getComplexType( @Path( ID ) UUID complexTypeId );

    @GET( BASE + COMPLEX_TYPE_PATH + ID_PATH + HIERARCHY_PATH )
    Set<ComplexType> getComplexTypeHierarchy( @Path( ID ) UUID complexTypeId );

    @DELETE( BASE + COMPLEX_TYPE_PATH + ID_PATH )
    Void deleteComplexType( @Path( ID ) UUID complexTypeId );

    /**
     * Get all entity types.
     *
     * @return Iterable containing all entity types.
     */
    @GET( ENTITY_TYPE_BASE_PATH )
    Iterable<EntityType> getEntityTypes();

    /**
     * Get all association entity types.
     *
     * @return Iterable containing all association entity types.
     */
    @GET( ASSOCIATION_TYPE_BASE_PATH )
    Iterable<EntityType> getAssociationEntityTypes();

    /**
     * Creates an entity type if it doesn't already exist.
     *
     * @param entityType The entity type to create.
     * @return ID for the entity type created.
     */
    @POST( ENTITY_TYPE_BASE_PATH )
    UUID createEntityType( @Body EntityType entityType );

    /**
     * Gets entity type details for a entity type with a given ID.
     *
     * @param entityTypeId ID for a given entity type.
     * @return An entity type with a given UUID.
     */
    @GET( ENTITY_TYPE_BASE_PATH + ID_PATH )
    EntityType getEntityType( @Path( ID ) UUID entityTypeId );

    /**
     * Gets entity type hierarchy. Returns a set of Entity types and its base types.
     *
     * @param entityTypeId ID for a given entity type.
     * @return A set of entity types and their corresponding base types.
     */
    @GET( ENTITY_TYPE_BASE_PATH + ID_PATH + HIERARCHY_PATH )
    Set<EntityType> getEntityTypeHierarchy( @Path( ID ) UUID entityTypeId );

    /**
     * Deletes an entity type with a given ID.
     *
     * @param entityTypeId The entity type ID to delete.
     */
    @DELETE( ENTITY_TYPE_BASE_PATH + ID_PATH )
    Void deleteEntityType( @Path( ID ) UUID entityTypeId );

    /**
     * Adds a property type with a given ID to an entity type with a given ID.
     *
     * @param entityTypeId The entity ID for the entity set that will have a property added to it.
     * @param propertyTypeId The property ID for the property that will be added to the entity type.
     */
    @PUT( ENTITY_TYPE_BASE_PATH + ENTITY_TYPE_ID_PATH + PROPERTY_TYPE_ID_PATH )
    Void addPropertyTypeToEntityType(
            @Path( ENTITY_TYPE_ID ) UUID entityTypeId,
            @Path( PROPERTY_TYPE_ID ) UUID propertyTypeId );

    /**
     * Removes a property type with a given ID from an entity type with a given ID.
     *
     * @param entityTypeId The entity ID for the entity set that will have a property removed from it.
     * @param propertyTypeId The property ID for the property that will be removed from the entity type.
     */
    @DELETE( ENTITY_TYPE_BASE_PATH + ENTITY_TYPE_ID_PATH + PROPERTY_TYPE_ID_PATH )
    Void removePropertyTypeFromEntityType(
            @Path( ENTITY_TYPE_ID ) UUID entityTypeId,
            @Path( PROPERTY_TYPE_ID ) UUID propertyTypeId );

    /**
     * Gets all entity sets available to the calling user.
     * @return Iterable containing entity sets available to the calling user.
     */
    @GET( ENTITY_SETS_BASE_PATH )
    Iterable<EntitySet> getEntitySets();

    /**
     * Creates multiple entity sets if they do not exist.
     *
     * @param entitySets The entity sets to create.
     * @return The entity sets created with UUIDs.
     */
    @POST( ENTITY_SETS_BASE_PATH )
    Map<String, UUID> createEntitySets( @Body Set<EntitySet> entitySets );

    /**
     * Get entity set ID, entity type ID, name, title, description, and contacts list for a given entity set.
     *
     * @param entitySetId The ID for the entity set.
     */
    @GET( ENTITY_SETS_BASE_PATH + ID_PATH )
    EntitySet getEntitySet( @Path( ID ) UUID entitySetId );

    @DELETE( ENTITY_SETS_BASE_PATH + ID_PATH )
    Void deleteEntitySet( @Path( ID ) UUID entitySetId );

    /**
     * Creates an empty schema, if it doesn't exist. If schema exists then no action is taken.
     *
     * @param namespace The namespace for the schema.
     * @param name The name for the schema.
     */
    @PUT( SCHEMA_BASE_PATH + NAMESPACE_PATH + NAME_PATH )
    Void createEmptySchema( @Path( NAMESPACE ) String namespace, @Path( NAME ) String name );

    @POST( SCHEMA_BASE_PATH )
    Void createSchemaIfNotExists( @Body Schema schema );

    /**
     * Gets all schemas.
     *
     * @return An iterable containing all the schemas available to the calling user.
     */
    @GET( SCHEMA_BASE_PATH )
    Iterable<Schema> getSchemas();

    /**
     * Gets all schemas associated with a given namespace and accessible by the caller.
     *
     * @param namespace The namespace for which to retrieve all accessible schemas.
     * @return All accessible schemas in the provided namespace.
     */
    @GET( SCHEMA_BASE_PATH + NAMESPACE_PATH )
    Iterable<Schema> getSchemasInNamespace( String namespace );

    /**
     * Gets the schema contents for a corresponding namespace and name
     *
     * @param namespace The namespace for a schema.
     * @param name The name for a schema.
     * @return All schemas identified by namespace and name, across all accessible Acls.
     */
    @GET( SCHEMA_BASE_PATH + NAMESPACE_PATH + NAME_PATH )
    Schema getSchemaContents(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name );

    /**
     * Gets the schema contents for a corresponding namespace and name
     *
     * @param namespace The namespace for a schema.
     * @param name The name for a schema.
     * @param fileType
     * @param token
     * @return All schemas identified by namespace and name, across all accessible Acls.
     */
    @GET( SCHEMA_BASE_PATH + NAMESPACE_PATH + NAME_PATH )
    Schema getSchemaContentsFormatted(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name,
            @Query( FILE_TYPE ) FileType fileType,
            @Query( TOKEN ) String token );

    /**
     * Edits the schema contents for a corresponding namespace and name
     *
     * @param namespace The namespace for a schema.
     * @param name The name for a schema.
     * @param request
     */
    @PATCH( SCHEMA_BASE_PATH + NAMESPACE_PATH + NAME_PATH )
    Void updateSchema(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name,
            @Body EdmRequest request );

    /**
     * Get ID for entity set with given name.
     *
     * @param entitySetName The name of the entity set.
     * @return ID for entity set.
     */
    @GET( BASE + IDS_PATH + ENTITY_SETS_PATH + NAME_PATH )
    UUID getEntitySetId( @Path( NAME ) String entitySetName );

    /**
     * Get ID for property type with given namespace and name.
     *
     * @param namespace The namespace for a property.
     * @param name The name for a property.
     * @return ID for property type.
     */
    @GET( BASE + IDS_PATH + PROPERTY_TYPE_PATH + NAMESPACE_PATH + NAME_PATH )
    UUID getPropertyTypeId( @Path( NAMESPACE ) String namespace, @Path( NAME ) String name );

    /**
     * Get ID for entity type with given namespace and name.
     *
     * @param namespace The namespace for a property.
     * @param name The name for a property.
     * @return ID for property type.
     */
    @GET( BASE + IDS_PATH + ENTITY_TYPE_PATH + NAMESPACE_PATH + NAME_PATH )
    UUID getEntityTypeId( @Path( NAMESPACE ) String namespace, @Path( NAME ) String name );

    /**
     * Edit property type metadata for a given property type.
     *
     * @param propertyTypeId ID for property type.
     * @param update Only title, description, and type fields are accepted. Other fields are ignored.
     */
    @PATCH( PROPERTY_TYPE_BASE_PATH + ID_PATH )
    Void updatePropertyTypeMetadata( @Path( ID ) UUID propertyTypeId, @Body MetadataUpdate update );

    /**
     * Edit entity type metadata for a given entity type.
     *
     * @param entityTypeId ID for entity type.
     * @param update Only title, description, and type fields are accepted. Other fields are ignored.
     */
    @PATCH( ENTITY_TYPE_BASE_PATH + ID_PATH )
    Void updateEntityTypeMetadata( @Path( ID ) UUID entityTypeId, @Body MetadataUpdate update );

    /**
     * Edit entity set metadata for a given entity set.
     *
     * @param entitySetId ID for entity set.
     * @param update Only title, description, contacts and name fields are accepted. Other fields are ignored.
     */
    @PATCH( ENTITY_SETS_BASE_PATH + ID_PATH )
    Void updateEntitySetMetadata( @Path( ID ) UUID entitySetId, @Body MetadataUpdate update );

    /**
     * Create edge type if it doesn't exist.
     *
     * @param linkingType
     * @return ID for the edge type.
     */
    @POST( ASSOCIATION_TYPE_BASE_PATH )
    UUID createEdgeType( @Body EdgeType linkingType );

    /**
     * Delete edge type with a given ID.
     *
     * @param linkingTypeId
     */
    @DELETE( ASSOCIATION_TYPE_BASE_PATH + ID_PATH )
    Void deleteEdgeType( @Path( ID ) UUID linkingTypeId );

    /**
     * Get edge type by given ID.
     *
     * @param linkingTypeId
     * @return EdgeType
     */
    @GET( ASSOCIATION_TYPE_BASE_PATH + ID_PATH )
    EdgeType getEdgeTypeById( @Path( ID ) UUID linkingTypeId );

}
