package com.dataloom.data;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.requests.CreateEntityRequest;
import com.dataloom.data.requests.LookupEntitiesRequest;
import com.google.common.collect.Multimap;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataApi {
    String CONTROLLER                      = "data";

    String FULLQUALIFIEDNAME               = "fqn";
    String NAME                            = "name";
    String NAME_SPACE                      = "namespace";
    String SET_NAME                        = "setname";

    String MULTIPLE                        = "multiple";
    String SELECTED                        = "selected";
    String ENTITYSET                       = "entityset";
    String ENTITY_DATA                     = "entitydata";
    String FILTERED                        = "filtered";
    String INTEGRATION                     = "integration";
    String FULLQUALIFIEDNAME_PATH          = "{" + FULLQUALIFIEDNAME + "}";
    String FULLQUALIFIEDNAME_PATH_WITH_DOT = "{" + FULLQUALIFIEDNAME + ":.+}";
    String NAME_PATH                       = "{" + NAME + "}";
    String NAME_SPACE_PATH                 = "{" + NAME_SPACE + "}";
    String SET_NAME_PATH                   = "{" + SET_NAME + "}";

    @GET( CONTROLLER + "/" + ENTITY_DATA + "/" + NAME_SPACE_PATH + "/" + NAME_PATH + "/" + SET_NAME_PATH )
    Iterable<Multimap<FullQualifiedName, Object>> getAllEntitiesOfEntitySet(
            @Path( SET_NAME ) String entitySetName,
            @Path( NAME_SPACE ) String entityTypeNamespace,
            @Path( NAME ) String entityTypeName );

    @PUT( CONTROLLER + "/" + ENTITY_DATA + "/" + NAME_SPACE_PATH + "/" + NAME_PATH + "/" + SET_NAME_PATH + "/" + SELECTED )
    Iterable<Multimap<FullQualifiedName, Object>> getSelectedEntitiesOfEntitySet(
            @Path( SET_NAME ) String entitySetName,
            @Path( NAME_SPACE ) String entityTypeNamespace,
            @Path( NAME ) String entityTypeName,
            @Body Set<FullQualifiedName> selectedProperties );

    @PUT( CONTROLLER + "/" + ENTITY_DATA )
    Iterable<Multimap<FullQualifiedName, Object>> getAllEntitiesOfType( @Body FullQualifiedName fqn );

    @PUT( CONTROLLER + "/" + ENTITY_DATA + "/" + MULTIPLE )
    Iterable<Iterable<Multimap<FullQualifiedName, Object>>> getAllEntitiesOfTypes(
            @Body List<FullQualifiedName> fqns );

    @GET( CONTROLLER + "/" + ENTITY_DATA + "/" + FULLQUALIFIEDNAME_PATH )
    Iterable<Multimap<FullQualifiedName, Object>> getAllEntitiesOfType(
            @Path( FULLQUALIFIEDNAME ) String fqnAsString );

    @GET( CONTROLLER + "/" + ENTITY_DATA + "/" + NAME_SPACE_PATH + "/" + NAME_PATH )
    Iterable<Multimap<FullQualifiedName, Object>> getAllEntitiesOfType(
            @Path( NAME_SPACE ) String namespace,
            @Path( NAME ) String name );

    @PUT( CONTROLLER + "/" + ENTITY_DATA + "/" + NAME_SPACE_PATH + "/" + NAME_PATH + "/" + SELECTED )
    Iterable<Multimap<FullQualifiedName, Object>> getSelectedEntitiesOfType(
            @Path( NAME_SPACE ) String namespace,
            @Path( NAME ) String name,
            @Body Set<FullQualifiedName> selectedProperties );

    @PUT( CONTROLLER + "/" + ENTITY_DATA + "/" + FILTERED )
    /**
     *
     * @param obj ObjectNode that builds a LookupEntitiesRequest. Should be JSON of the form
     *            SerializationConstants.USER_ID (userId): UUID, SerializationConstants.TYPE_FIELD (type):
     *            Set\<FullQualifiedName\>, SerializationConstants.PROPERTIES_FIELD (properties): Map\<FullQualifiedName
     *            as String, Object\>
     * @return Iterable of UUID matching the request
     */
    Iterable<UUID> getFilteredEntities( @Body LookupEntitiesRequest lookupEntitiesRequest );

    @POST( CONTROLLER + "/" + ENTITY_DATA )
    Void createEntityData( @Body CreateEntityRequest createEntityRequest );

    @GET( CONTROLLER + "/" + INTEGRATION )
    Map<String, String> getAllIntegrationScripts();

    @PUT( CONTROLLER + "/" + INTEGRATION )
    Map<String, String> getIntegrationScript( @Body Set<String> url );

    @POST( CONTROLLER + "/" + INTEGRATION )
    Void createIntegrationScript( @Body Map<String, String> integrationScripts );

}
