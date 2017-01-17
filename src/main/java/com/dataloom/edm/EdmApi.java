package com.dataloom.edm;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.dataloom.edm.internal.EntitySet;
import com.dataloom.edm.internal.EntityType;
import com.dataloom.edm.internal.PropertyType;
import com.dataloom.edm.internal.Schema;
import com.dataloom.edm.requests.EdmRequest;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public interface EdmApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE                 = "/datastore";
    String CONTROLLER              = "/edm";
    String BASE                    = SERVICE + CONTROLLER;

    /*
     * These are the actual components after {SERVICE}/{CONTROLLER}/
     */
    String ID                      = "id";
    String NAME                    = "name";
    String NAMESPACE               = "namespace";
    String NAMESPACES              = "namespaces";
    String ENTITY_SETS             = "entitySets";
    String ENTITY_TYPES            = "entityTypes";
    String PROPERTY_TYPES          = "propertyTypes";
    String SCHEMA                  = "schema";
    String SCHEMAS                 = "schemas";

    // {namespace}/{schema_name}/{class}/{FQN}/{FQN}
    /*
     * /entity/type/{namespace}/{name} /entity/set/{namespace}/{name} /schema/{namespace}/{name}
     * /property/{namespace}/{name}
     */
    String IDS_PATH                = "/ids";
    String SCHEMA_PATH             = "/schema";
    String ENTITY_SETS_PATH        = "/entity/set";
    String ENTITY_TYPE_PATH        = "/entity/type";
    String PROPERTY_TYPE_PATH      = "/property/type";
    String NAMESPACE_PATH          = "/{" + NAMESPACE + "}";
    String NAME_PATH               = "/{" + NAME + "}";
    String ID_PATH                 = "/{" + ID + "}";
    String DETAILS_PATH            = "/details";

    String SCHEMA_BASE_PATH        = BASE + SCHEMA_PATH;
    String ENTITY_SETS_BASE_PATH   = BASE + ENTITY_SETS_PATH;
    String ENTITY_TYPE_BASE_PATH   = BASE + ENTITY_TYPE_PATH;
    String PROPERTY_TYPE_BASE_PATH = BASE + PROPERTY_TYPE_PATH;

    @GET( BASE )
    EntityDataModel getEntityDataModel();

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

    @GET( ENTITY_TYPE_BASE_PATH )
    Iterable<EntityType> getEntityTypes();

    @GET( ENTITY_TYPE_BASE_PATH + ID_PATH )
    EntityType getEntityType( @Path( ID ) UUID entityTypeId );

    /**
     * Creates an entity type if it doesn't already exist.
     *
     * @param entityType The entity type to create.
     */
    @POST( ENTITY_TYPE_BASE_PATH )
    UUID createEntityType( @Body EntityType entityType );

    @DELETE( ENTITY_TYPE_BASE_PATH + ID_PATH )
    Void deleteEntityType( @Path( ID ) UUID entityTypeId );

    @PUT( ENTITY_TYPE_BASE_PATH + ID_PATH )
    Void updatePropertyTypesInEntityType(
            @Path( ID ) UUID entityTypeId,
            @Body Set<UUID> request );

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
     * Loads schemas from the server.
     * 
     * @param namespace A valid entity data model name space.
     * @return All schemas in the name space specified {@code namespace} parameter.
     */
    @GET( SCHEMA_BASE_PATH + NAMESPACE_PATH )
    Iterable<Schema> getSchemas( @Path( NAMESPACE ) String namespace );

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

    @PATCH( SCHEMA_BASE_PATH + NAMESPACE_PATH + NAME_PATH + "/" )
    Void updateSchema(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name,
            @Body EdmRequest request );

    @GET( BASE + ENTITY_SETS_PATH + NAME_PATH )
    UUID getEntitySetId( @Path( NAME ) String entitySetName );

    @GET( BASE + IDS_PATH + PROPERTY_TYPE_PATH + NAMESPACE_PATH + NAME_PATH )
    UUID getPropertyTypeId( @Path( NAMESPACE ) String namespace, @Path( NAME ) String name );

    @GET( BASE + IDS_PATH + ENTITY_TYPE_PATH + NAMESPACE_PATH + NAME_PATH )
    UUID getEntityTypeId( @Path( NAMESPACE ) String namespace, @Path( NAME ) String name );

}
