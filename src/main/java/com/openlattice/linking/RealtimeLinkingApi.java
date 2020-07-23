/*
 * Copyright (C) 2019. OpenLattice, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * You can contact the owner of the copyright at support@openlattice.com
 *
 */

package com.openlattice.linking;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * This API is responsible for any real-time linking / matching requests
 * Currently it is only used in rehearsal tests
 */
public interface RealtimeLinkingApi {

    String SERVICE               = "/linker";
    String CONTROLLER            = "/linking";
    String BASE                  = SERVICE + CONTROLLER;

    String MISSING               = "/missing";
    String FINISHED              = "/finished";
    String MATCHED               = "/matched";

    String SET                   = "/set";
    String LINKING_ID            = "linkingId";
    String LINKING_ID_PATH       = "/{" + LINKING_ID + "}";


    @GET( BASE + FINISHED + SET )
    Set<UUID> getLinkingFinishedEntitySets();

    @GET( BASE + MATCHED + LINKING_ID_PATH )
    Set<MatchedEntityPair> getMatchedEntitiesForLinkingId( @Path( LINKING_ID ) UUID linkingId );

    /**
     * Get counts of entities that are queued to be linked, grouped by entity set.  Records that
     * have been updated after linking, are queued to be linked.
     *
     * @param entitySetIds A set of normal entity set ids
     * @return A map with entitySetIds as keys, and as values a count of the entityKeyIds that haven't been linked yet.
     */
    @POST( BASE + MISSING )
    Map<UUID, Long> getEntitySetNotYetLinkedCount(
            @Body Set<UUID> entitySetIds
    );


}
