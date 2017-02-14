/*
 * Copyright 2017 Kryptnostic, Inc. (dba Loom)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.dataloom.linking;

import com.dataloom.data.EntityKey;
import com.dataloom.edm.set.LinkingEntitySet;
import com.dataloom.edm.type.LinkingEntityType;
import retrofit2.http.*;

import java.util.Set;
import java.util.UUID;

/**
 * This API is used for creating and managing synthetic entity sets created by linking several entity sets together.
 * The entity sets created by this invoking this API are not actually instantiated until they are accessed via the
 * {@link com.dataloom.data.DataApi}, which dynamically weaves them together based on the matching information.
 *
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public interface LinkingApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE    = "/datastore";
    String CONTROLLER = "/linking";
    String BASE       = SERVICE + CONTROLLER;

    String SET_ID           = "setId";
    String SYNC_ID          = "syncId";
    String ENTITY_ID        = "entityId";
    String SET              = "set";
    String LINKED_ENTITY_ID = "linkedEntityId";
    String TYPE             = "type";

    /**
     * Creates an entity type describing the result of linking several entity types together. This entity type will be
     * be usable in entity set searches.
     *
     * @param linkingEntityType The linking entity type
     * @return The {@link UUID} of the linked entity type.
     */
    @POST( BASE + "/" + TYPE )
    UUID createLinkingEntityType( LinkingEntityType linkingEntityType );

    /**
     * Performs linking operation on entity sets. The entity type of the set must have already been defined by a call to
     * {@link LinkingApi#createLinkingEntityType(LinkingEntityType)}.
     *
     * @param linkingProperties A set of entity set associated properties to link on. Each map must be the same length,
     *                          with entity set ids as a keys and property type ids as values. If no maps are provided, an
     *                          empty linking entity set is created that can be populated by calling
     *                          {@link LinkingApi#linkEntities(UUID, UUID, UUID, Set)}.
     * @return The id of the new entity set constructed from linking the desired entity sets.
     */
    @POST( BASE + "/" + SET )
    UUID linkEntitySets( @Body LinkingEntitySet linkingEntitySet );

    /**
     * Links a set of entities into a new linked entity.
     *
     * @param syncId
     * @param entitySetId
     * @param entityId
     * @param entities
     * @return The entity id of the new linked entity id
     */
    @POST( BASE + "/" + SET + "/{" + SYNC_ID + "}/{" + SET_ID + "}/{" + ENTITY_ID + "}" )
    UUID linkEntities(
            @Path( SYNC_ID ) UUID syncId,
            @Path( SET_ID ) UUID entitySetId,
            @Path( ENTITY_ID ) UUID entityId,
            @Body Set<EntityKey> entities );

    @PUT( BASE + "/" + SET + "/{" + SYNC_ID + "}/{" + SET_ID + "}/{" + ENTITY_ID + "}" )
    Void setLinkedEntities(
            @Path( SYNC_ID ) UUID syncId,
            @Path( SET_ID ) UUID entitySetId,
            @Path( ENTITY_ID ) UUID entityId,
            @Body Set<EntityKey> entities );

    @DELETE( BASE + "/" + SET + "/{" + SYNC_ID + "}/{" + SET_ID + "}/{" + ENTITY_ID + "}" )
    Void deleteLinkedEntities(
            @Path( SYNC_ID ) UUID syncId,
            @Path( SET_ID ) UUID entitySetId,
            @Path( ENTITY_ID ) UUID entityId
    );

    @PUT( BASE + "/" + SET + "/{" + SYNC_ID + "}/{" + SET_ID + "}/{" + ENTITY_ID + "}/{" + LINKED_ENTITY_ID + "}" )
    Void addLinkedEntities(
            @Path( SYNC_ID ) UUID syncId,
            @Path( SET_ID ) UUID entitySetId,
            @Path( ENTITY_ID ) UUID entityId,
            @Path( LINKED_ENTITY_ID ) UUID linkedEntityId );

    @DELETE( BASE + "/" + SET + "/{" + SYNC_ID + "}/{" + SET_ID + "}/{" + ENTITY_ID + "}/{" + LINKED_ENTITY_ID + "}" )
    Void removeLinkedEntity(
            @Path( SYNC_ID ) UUID syncId,
            @Path( SET_ID ) UUID entitySetId,
            @Path( ENTITY_ID ) UUID entityId,
            @Path( LINKED_ENTITY_ID ) UUID linkedEntityId );

}
