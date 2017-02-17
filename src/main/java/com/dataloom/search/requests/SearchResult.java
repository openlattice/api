package com.dataloom.search.requests;

import java.util.List;
import java.util.Map;

public class SearchResult {
    
    private final long numHits;
    private final List<Map<String, Object>> hits;
    
    public SearchResult( long numHits, List<Map<String, Object>> hits ) {
        this.numHits = numHits;
        this.hits = hits;
    }
    
    public long getNumHits() {
        return numHits;
    }
    
    public List<Map<String, Object>> getHits() {
        return hits;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.valueOf( numHits ).hashCode();
        result = prime * result + ( ( hits == null ) ? 0 : hits.hashCode() );
        return result;
    }
    
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( !( obj instanceof SearchResult ) ) {
            return false;
        }
        SearchResult other = (SearchResult) obj;
        if ( numHits != other.numHits ) {
            return false;
        }
        if ( !hits.equals( other.hits ) ) {
            return false;
        }
        return true;
    }
}