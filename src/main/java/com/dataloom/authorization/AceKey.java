package com.dataloom.authorization;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public class AceKey {
    private final List<UUID> keys;
    private final Principal    principal;

    /**
     * This is a special constructor that puts the keys from a set in an canonical order
     * 
     * @param key Set of object key to put in canonical order
     * @param principal The principal for this ACE
     */
    public AceKey( List<UUID> key, Principal principal ) {
        this.keys = checkNotNull( key );
        this.principal = checkNotNull( principal );
        checkArgument( key.size() > 0, "At least one key must be provided." );
    }

    public AceKey( Principal principal, UUID... key ) {
        this( Arrays.asList( key ), principal );
    }

    public List<UUID> getKey() {
        return keys;
    }

    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( keys == null ) ? 0 : keys.hashCode() );
        result = prime * result + ( ( principal == null ) ? 0 : principal.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof AceKey ) ) {
            return false;
        }
        AceKey other = (AceKey) obj;
        if ( keys == null ) {
            if ( other.keys != null ) {
                return false;
            }
        } else if ( !keys.equals( other.keys ) ) {
            return false;
        }
        if ( principal == null ) {
            if ( other.principal != null ) {
                return false;
            }
        } else if ( !principal.equals( other.principal ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AceKey [keys=" + keys + ", principal=" + principal + "]";
    }
}
