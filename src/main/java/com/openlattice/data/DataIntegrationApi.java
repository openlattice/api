/*
 * Copyright (C) 2018-2020. OpenLattice, Inc.
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
 * You can contact the owner of the copyright at support@openlattice.com
 */

package com.openlattice.data;

import com.openlattice.data.integration.*;

import java.util.*;

import retrofit2.http.*;

@Deprecated
public interface DataIntegrationApi {

    /*
     * These determine the service routing for the LB
     */

    // @formatter:off
    String SERVICE               = "/datastore";
    String CONTROLLER            = "/integration";
    String BASE                  = SERVICE + CONTROLLER;
    // @formatter:on

    String ASSOCIATION        = "association";
    String DETAILED_RESULTS   = "detailedResults";
    String EDGES              = "edges";
    String ENTITY_KEY_IDS     = "entityKeyIds";
    String ENTITY_SET         = "set";
    String ENTITY_SET_ID      = "setId";
    String ENTITY_SET_ID_PATH = "{" + ENTITY_SET_ID + "}";
    String S3                 = "s3";

    @POST( BASE + "/" + ENTITY_SET + "/" + ENTITY_SET_ID_PATH )
    IntegrationResults integrateEntities(
            @Path( ENTITY_SET_ID ) UUID entitySetId,
            @Query( DETAILED_RESULTS ) boolean detailedResults,
            @Body Map<String, Map<UUID, Set<Object>>> entities );

    /**
     * Creates a new set of associations.
     *
     * @param associations Set of associations to create. An association is the usual (String entityId, SetMultimap &lt;
     *                     UUID, Object &gt; details of entity) pairing enriched with source/destination EntityKeys
     */
    @POST( BASE + "/" + ASSOCIATION + "/" + ENTITY_SET_ID_PATH )
    IntegrationResults integrateAssociations(
            @Body Set<Association> associations,
            @Query( DETAILED_RESULTS ) boolean detailedResults );

    @POST( BASE )
    IntegrationResults integrateEntityAndAssociationData(
            @Body BulkDataCreation data,
            @Query( DETAILED_RESULTS ) boolean detailedResults );

    @POST( BASE + "/" + S3 )
    List<String> generatePresignedUrls( @Body Collection<S3EntityData> data );

    @POST( BASE + "/" + ENTITY_KEY_IDS )
    List<UUID> getEntityKeyIds( @Body Set<EntityKey> entityKeys );

    @PUT( BASE + "/" + EDGES )
    int createEdges( @Body Set<DataEdgeKey> edges );

}
