/*
 * Copyright (C) 2018. OpenLattice, Inc.
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

package com.openlattice.search.requests;

import com.openlattice.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openlattice.search.SearchApi;

import java.util.Optional;

public class SearchTerm {

    private final String  searchTerm;
    private final int     start;
    private final int     maxHits;
    private final boolean fuzzy;

    public SearchTerm(
            @JsonProperty( SerializationConstants.SEARCH_TERM ) String searchTerm,
            @JsonProperty( SerializationConstants.START ) int start,
            @JsonProperty( SerializationConstants.MAX_HITS ) int maxHits,
            @JsonProperty( SerializationConstants.FUZZY ) Optional<Boolean> fuzzy ) {
        this.searchTerm = searchTerm.trim();
        this.start = start;
        this.maxHits = Math.min( maxHits, SearchApi.MAX_SEARCH_RESULTS );
        this.fuzzy = fuzzy.orElse( true );
    }

    public SearchTerm( String searchTerm, int start, int maxHits ) {
        this( searchTerm, start, maxHits, Optional.empty() );
    }

    @JsonProperty( SerializationConstants.SEARCH_TERM )
    public String getSearchTerm() {
        return searchTerm;
    }

    @JsonProperty( SerializationConstants.START )
    public int getStart() {
        return start;
    }

    @JsonProperty( SerializationConstants.MAX_HITS )
    public int getMaxHits() {
        return maxHits;
    }

    @JsonProperty( SerializationConstants.FUZZY )
    public boolean getFuzzy() {
        return fuzzy;
    }
}
