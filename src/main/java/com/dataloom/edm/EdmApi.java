package com.dataloom.edm;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.dataloom.edm.requests.EdmDetailsSelector;
import com.dataloom.edm.requests.EdmRequest;
import com.dataloom.edm.requests.MetadataUpdate;
import com.dataloom.edm.type.ComplexType;
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
    String ID                      = "id";
    String ENTITY_TYPE_ID          = "entityTypeId";
    String PROPERTY_TYPE_ID        = "propertyTypeId";
    String NAME                    = "name";
    String NAMESPACE               = "namespace";
    String NAMESPACES              = "namespaces";
    String ENTITY_SETS             = "entitySets";
    String ENTITY_TYPES            = "entityTypes";
    String PROPERTY_TYPES          = "propertyTypes";
    String SCHEMA                  = "schema";
    String SCHEMAS                 = "schemas";
    String FILE_TYPE               = "fileType";
    String TOKEN                   = "token";

    // {namespace}/{schema_name}/{class}/{FQN}/{FQN}
    /*
     * /entity/type/{namespace}/{name} /entity/set/{namespace}/{name} /schema/{namespace}/{name}
     * /property/{namespace}/{name}
     */

    String IDS_PATH                = "/ids";
    String SCHEMA_PATH             = "/schema";
    String ENUM_TYPE_PATH          = "/enum/type";
    String ENTITY_SETS_PATH        = "/entity/set";
    String ENTITY_TYPE_PATH        = "/entity/type";
    String PROPERTY_TYPE_PATH      = "/property/type";
    String COMPLEX_TYPE_PATH       = "/complex/type";
    String HIERARCHY_PATH          = "/hierarchy";

    String NAMESPACE_PATH          = "/{" + NAMESPACE + "}";
    String NAME_PATH               = "/{" + NAME + "}";
    String ID_PATH                 = "/{" + ID + "}";
    String ENTITY_TYPE_ID_PATH     = "/{" + ENTITY_TYPE_ID + "}";
    String PROPERTY_TYPE_ID_PATH   = "/{" + PROPERTY_TYPE_ID + "}";

    String SCHEMA_BASE_PATH        = BASE + SCHEMA_PATH;
    String ENTITY_SETS_BASE_PATH   = BASE + ENTITY_SETS_PATH;
    String ENTITY_TYPE_BASE_PATH   = BASE + ENTITY_TYPE_PATH;
    String PROPERTY_TYPE_BASE_PATH = BASE + PROPERTY_TYPE_PATH;

    @GET( BASE )
    EntityDataModel getEntityDataModel();

    @POST( BASE )
    EdmDetails getEdmDetails( @Body Set<EdmDetailsSelector> selectors );

    @GET( PROPERTY_TYPE_BASE_PATH )
    Iterable<PropertyType> getPropertyTypes();

    @GET( PROPERTY_TYPE_BASE_PATH + ID_PATH )
    PropertyType getPropertyType( @Path( ID ) UUID propertyTypeId );

    @GET( PROPERTY_TYPE_BASE_PATH + "/" + NAMESPACE + NAMESPACE_PATH )
    Iterable<PropertyType> getPropertyTypesInNamespace( String namespace );

    /**
     * Creates a property type if it doesn't exist. If property type already exists, then no action is taken.
     *
     * @param propertyType The property to create.
     */
    @POST( PROPERTY_TYPE_BASE_PATH )
    UUID createPropertyType( @Body PropertyType propertyType );

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

    @GET( ENTITY_TYPE_BASE_PATH )
    Iterable<EntityType> getEntityTypes();

    /**
     * Creates an entity type if it doesn't already exist.
     *
     * @param entityType The entity type to create.
     */
    @POST( ENTITY_TYPE_BASE_PATH )
    UUID createEntityType( @Body EntityType entityType );

    @GET( ENTITY_TYPE_BASE_PATH + ID_PATH )
    EntityType getEntityType( @Path( ID ) UUID entityTypeId );

    @GET( ENTITY_TYPE_BASE_PATH + ID_PATH + HIERARCHY_PATH )
    Set<EntityType> getEntityTypeHierarchy( @Path( ID ) UUID entityTypeId );

    @DELETE( ENTITY_TYPE_BASE_PATH + ID_PATH )
    Void deleteEntityType( @Path( ID ) UUID entityTypeId );

    @PUT( ENTITY_TYPE_BASE_PATH + ENTITY_TYPE_ID_PATH + PROPERTY_TYPE_ID_PATH )
    Void addPropertyTypeToEntityType(
            @Path( ENTITY_TYPE_ID ) UUID entityTypeId,
            @Path( PROPERTY_TYPE_ID ) UUID propertyTypeId );

    @DELETE( ENTITY_TYPE_BASE_PATH + ENTITY_TYPE_ID_PATH + PROPERTY_TYPE_ID_PATH )
    Void removePropertyTypeFromEntityType(
            @Path( ENTITY_TYPE_ID ) UUID entityTypeId,
            @Path( PROPERTY_TYPE_ID ) UUID propertyTypeId );

    /**
     * @return All entity sets available to the calling user.
     */
    @GET( ENTITY_SETS_BASE_PATH )
    Iterable<EntitySet> getEntitySets();

    /**
     * Creates multiple entity sets, if they do not exist.
     *
     * @param entitySets The entity sets to create.
     */
    @POST( ENTITY_SETS_BASE_PATH )
    Map<String, UUID> createEntitySets( @Body Set<EntitySet> entitySets );

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
     * Retrieves all schemas associated with a given namespace and accessible by the caller.
     *
     * @param namespace The namespace for which to retrieve all accessible schemas.
     * @return All accessible schemas in the provided namespace.
     */
    @GET( SCHEMA_BASE_PATH + NAMESPACE_PATH )
    Iterable<Schema> getSchemasInNamespace( String namespace );

    /**
     * Retrieves the schema contents for a corresponding namespace
     *
     * @param namespace
     * @param name
     * @return All schemas identified by namespace and name, across all accessible Acls.
     */
    @GET( SCHEMA_BASE_PATH + NAMESPACE_PATH + NAME_PATH )
    Schema getSchemaContents(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name );

    @GET( SCHEMA_BASE_PATH + NAMESPACE_PATH + NAME_PATH )
    Schema getSchemaContentsFormatted(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name,
            @Query( FILE_TYPE ) FileType fileType,
            @Query( TOKEN ) String token );

    @PATCH( SCHEMA_BASE_PATH + NAMESPACE_PATH + NAME_PATH )
    Void updateSchema(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name,
            @Body EdmRequest request );

    @GET( BASE + IDS_PATH + ENTITY_SETS_PATH + NAME_PATH )
    UUID getEntitySetId( @Path( NAME ) String entitySetName );

    @GET( BASE + IDS_PATH + PROPERTY_TYPE_PATH + NAMESPACE_PATH + NAME_PATH )
    UUID getPropertyTypeId( @Path( NAMESPACE ) String namespace, @Path( NAME ) String name );

    @GET( BASE + IDS_PATH + ENTITY_TYPE_PATH + NAMESPACE_PATH + NAME_PATH )
    UUID getEntityTypeId( @Path( NAMESPACE ) String namespace, @Path( NAME ) String name );

    /**
     * 
     * @param propertyTypeId
     * @param update Only title, description, and type fields are accepted. Other fields are ignored.
     * @return
     */
    @PATCH( PROPERTY_TYPE_BASE_PATH + ID_PATH )
    Void updatePropertyTypeMetadata( @Path( ID ) UUID propertyTypeId, @Body MetadataUpdate update );

    /**
     * 
     * @param entityTypeId
     * @param update Only title, description, and type fields are accepted. Other fields are ignored.
     * @return
     */
    @PATCH( ENTITY_TYPE_BASE_PATH + ID_PATH )
    Void updateEntityTypeMetadata( @Path( ID ) UUID entityTypeId, @Body MetadataUpdate update );

    /**
     * 
     * @param entitySetId
     * @param update Only title, description, contacts and name fields are accepted. Other fields are ignored.
     * @return
     */
    @PATCH( ENTITY_SETS_BASE_PATH + ID_PATH )
    Void updateEntitySetMetadata( @Path( ID ) UUID entitySetId, @Body MetadataUpdate update );
}
