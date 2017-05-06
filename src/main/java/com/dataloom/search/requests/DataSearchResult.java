package com.dataloom.search.requests;

import java.util.List;

import com.google.common.collect.SetMultimap;

public class DataSearchResult {
    private final long                      numHits;
    private final List<SetMultimap<Object, Object>> hits;

    public DataSearchResult( long numHits, List<SetMultimap<Object, Object>> hits ) {
        this.numHits = numHits;
        this.hits = hits;
    }

    public long getNumHits() {
        return numHits;
    }

    public List<SetMultimap<Object, Object>> getHits() {
        return hits;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( hits == null ) ? 0 : hits.hashCode() );
        result = prime * result + (int) ( numHits ^ ( numHits >>> 32 ) );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        DataSearchResult other = (DataSearchResult) obj;
        if ( hits == null ) {
            if ( other.hits != null ) return false;
        } else if ( !hits.equals( other.hits ) ) return false;
        if ( numHits != other.numHits ) return false;
        return true;
    }
}
