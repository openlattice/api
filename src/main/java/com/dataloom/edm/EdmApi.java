package com.dataloom.edm;

import java.util.Set;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.edm.internal.EntitySet;
import com.dataloom.edm.internal.EntitySetWithPermissions;
import com.dataloom.edm.internal.EntityType;
import com.dataloom.edm.internal.EntityTypeWithDetails;
import com.dataloom.edm.internal.PropertyType;
import com.dataloom.edm.internal.Schema;
import com.dataloom.edm.requests.GetSchemasRequest;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public interface EdmApi {
    String ALIAS          = "alias";
    String ACL_ID         = "aclId";
    String LOAD_DETAILS   = "loadDetails";
    String NAME           = "name";
    String NAMESPACE      = "namespace";
    String NAMESPACES     = "namespaces";
    String ENTITY_SETS    = "entitySets";
    String ENTITY_TYPES   = "objectTypes";
    String PROPERTY_TYPES = "propertyTypes";
    String SCHEMA         = "schema";
    String SCHEMAS        = "schemas";
    String IS_OWNER       = "isOwner";

    // {namespace}/{schema_name}/{class}/{FQN}/{FQN}
    /*
     * /entity/type/{namespace}/{name} /entity/set/{namespace}/{name} /schema/{namespace}/{name}
     * /property/{namespace}/{name}
     */
    String SCHEMA_BASE_PATH        = "schema";
    String ENTITY_SETS_BASE_PATH   = "entity/set";
    String ENTITY_TYPE_BASE_PATH   = "entity/type";
    String PROPERTY_TYPE_BASE_PATH = "property/type";
    String NAMESPACE_PATH          = "{" + NAMESPACE + "}";
    String NAME_PATH               = "{" + NAME + "}";

    String ADD_ENTITY_TYPES_PATH    = "addEntityTypes";
    String DELETE_ENTITY_TYPES_PATH = "deleteEntityTypes";
    String ADD_PROPERTY_TYPES_PATH    = "addPropertyTypes";
    String DELETE_PROPERTY_TYPES_PATH = "deletePropertyTypes";
    String DETAILS_PATH               = "details";

    @GET( "/" )
    EntityDataModel getEntityDataModel();

    /**
     * Creates an empty schema.
     *
     * @param namespace The namespace for the schema.
     * @param name The name for the schema.
     */
    @POST( SCHEMA_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH )
    Void createEmptySchema( @Path( NAMESPACE ) String namespace, @Path( NAME ) String name );

    /**
     * Retrieves schemas matching the namespace provided in the {@code request} parameter. If no namespace is specified
     * then all schemas will be returned.
     * <p>
     * The level of type detail returned by the server is determined those provided in the {@code typeDetails } field of
     * the request. If no type details are specified then the server return all type details.
     * <p>
     * The server will only return schemas that the calling user is authorized to see.
     *
     * @param request The request options to use when filtering schemas.
     * @return An iterable of Schema objects.
     */
    @POST( SCHEMA_BASE_PATH )
    Iterable<Schema> getSchemas( @Body GetSchemasRequest request );

    /**
     * Gets all schemas available to the calling user.
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
    @GET( SCHEMA_BASE_PATH + "/" + NAMESPACE_PATH )
    Iterable<Schema> getSchemasInNamespace( String namespace );

    /**
     * Retrieves the schema contents for a corresponding namespace
     *
     * @param namespace
     * @param name
     * @return All schemas identified by namespace and name, across all accessible Acls.
     */
    @GET( SCHEMA_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH )
    Schema getSchemaContents(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name );

    /**
     * @param namespace
     * @param name
     * @param entityTypes
     */
    @PUT( SCHEMA_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH + "/" + ADD_ENTITY_TYPES_PATH )
    Void addEntityTypesToSchema(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name,
            @Body Set<FullQualifiedName> entityTypes );

    @HTTP(method="DELETE", path = SCHEMA_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH + "/" + DELETE_ENTITY_TYPES_PATH, hasBody = true )
    Void removeEntityTypeFromSchema(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name,
            @Body Set<FullQualifiedName> entityTypes );

    @PUT( SCHEMA_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH + "/" + ADD_PROPERTY_TYPES_PATH )
    Void addPropertyTypesToSchema(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name,
            @Body Set<FullQualifiedName> properties );

    @HTTP(method="DELETE", path = SCHEMA_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH + "/" + DELETE_PROPERTY_TYPES_PATH, hasBody = true )
    Void removePropertyTypesFromSchema(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name,
            @Body Set<FullQualifiedName> properties );

    /**
     * Creates multiple entity sets, if they do not exist.
     *
     * @param entitySets The entity sets to create.
     */
    @POST( ENTITY_SETS_BASE_PATH )
    Void postEntitySets( @Body Set<EntitySet> entitySets );

    /**
     * Creates or updates multiple entity sets.
     *
     * @param entitySets The entity sets to create.
     */
    @PUT( ENTITY_SETS_BASE_PATH )
    Void putEntitySets( @Body Set<EntitySet> entitySets );

    /**
     * 
     * @param isOwner Optional. If isOwner is true, return all EntitySets user owns. If isOwner is false, return all EntitySetsWithPermissions user does not own. If isOwner is null, return all EntitySetsWithPermissions.
     * @return
     */
    @GET( ENTITY_SETS_BASE_PATH )
    Iterable<EntitySetWithPermissions> getEntitySets( @Query( IS_OWNER ) Boolean isOwner );

    @GET( ENTITY_SETS_BASE_PATH + "/" + NAME_PATH )
    EntitySet getEntitySet( @Path( NAME ) String entitySetName );

    @POST( ENTITY_SETS_BASE_PATH + "/" + NAME_PATH )
    Void assignEntityToEntitySet( @Path( NAME) String entitySetName, @Body Set<UUID> entityIds );

    @DELETE( ENTITY_SETS_BASE_PATH + "/" + NAME_PATH )
    Void deleteEntitySet( @Path( NAME ) String entitySetName );
    
    /**
     * Creates an entity type if it doesn't already exist.
     *
     * @param entityType the entity to create.
     */
    @POST( ENTITY_TYPE_BASE_PATH )
    Void postEntityType( @Body EntityType entityType );

    @PUT( ENTITY_TYPE_BASE_PATH )
    Void putEntityType( @Body EntityType entityType );

    @GET( ENTITY_TYPE_BASE_PATH )
    Iterable<EntityType> getEntityTypes();

    @GET( ENTITY_TYPE_BASE_PATH + "/" + DETAILS_PATH )
    Iterable<EntityTypeWithDetails> getEntityTypesWithDetails();

    @GET( ENTITY_TYPE_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH )
    EntityType getEntityType( @Path( NAMESPACE ) String namespace, @Path( NAME ) String entityTypeName );

    @DELETE( ENTITY_TYPE_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH )
    Void deleteEntityType( @Path( NAMESPACE ) String namespace, @Path( NAME ) String entityTypeName );

    /**
     * @param namespace
     * @param name
     * @param properties Set of (Existing) Property Types to add to EntityType
     */
    @PUT( ENTITY_TYPE_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH + "/" + ADD_PROPERTY_TYPES_PATH )
    Void addPropertyTypesToEntityType(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name,
            @Body Set<FullQualifiedName> properties
    );
    
    @HTTP(method="DELETE", path = ENTITY_TYPE_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH + "/" + DELETE_PROPERTY_TYPES_PATH, hasBody = true )
    Void removePropertyTypesFromEntityType(
            @Path( NAMESPACE ) String namespace,
            @Path( NAME ) String name,
            @Body Set<FullQualifiedName> properties
    );


    /**
     * Creates a property type if doesn't already exist.
     *
     * @param propertyType The property to create.
     */
    @POST( PROPERTY_TYPE_BASE_PATH )
    Void createPropertyType( @Body PropertyType propertyType );

    /**
     * @param typeInfo
     */
    @PUT( PROPERTY_TYPE_BASE_PATH )
    Void putPropertyType( @Body PropertyType typeInfo );

    @DELETE( PROPERTY_TYPE_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH )
    Void deletePropertyType( @Path( NAMESPACE ) String namespace, @Path( NAME ) String name );

    @GET( PROPERTY_TYPE_BASE_PATH + "/" + NAMESPACE_PATH + "/" + NAME_PATH )
    PropertyType getPropertyType( @Path( NAMESPACE ) String namespace, @Path( NAME ) String entityTypeName );

    @GET( PROPERTY_TYPE_BASE_PATH + "/" + NAMESPACE_PATH )
    Iterable<PropertyType> getPropertyTypesInNamespace( @Path( NAMESPACE ) String namespace );

    @GET( PROPERTY_TYPE_BASE_PATH )
    Iterable<PropertyType> getPropertyTypes();

}