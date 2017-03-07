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

package com.dataloom.authorization.securable;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public abstract class AbstractSecurableType extends AbstractSecurableObject {
    // This is only a descriptive property so relax finality.
    protected FullQualifiedName type;

    protected AbstractSecurableType(
            UUID id,
            FullQualifiedName type,
            String title,
            Optional<String> description ) {
        this( Optional.of( id ), type, title, description );
    }

    protected AbstractSecurableType(
            Optional<UUID> id,
            FullQualifiedName type,
            String title,
            Optional<String> description ) {
        super( id, title, description );
        this.type = checkNotNull( type );
    }

    public void rename( FullQualifiedName newType ) {
        type = newType;
    }
        
    @JsonProperty( SerializationConstants.TYPE_FIELD )
    public FullQualifiedName getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( !super.equals( obj ) ) {
            return false;
        }
        if ( !( obj instanceof AbstractSecurableType ) ) {
            return false;
        }
        AbstractSecurableType other = (AbstractSecurableType) obj;
        if ( type == null ) {
            if ( other.type != null ) {
                return false;
            }
        } else if ( !type.equals( other.type ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AbstractSecurableType [type=" + type + ", id=" + id + ", title=" + title + ", description="
                + description + "]";
    }

}
