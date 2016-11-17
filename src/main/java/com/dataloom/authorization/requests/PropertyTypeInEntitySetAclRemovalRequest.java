package com.dataloom.authorization.requests;

import java.util.Set;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyTypeInEntitySetAclRemovalRequest {
    
    @JsonProperty( SerializationConstants.NAME_FIELD)
    protected String entitySetName;
    @JsonProperty( SerializationConstants.PROPERTIES_FIELD)
    protected Set<FullQualifiedName> properties;
    
    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        PropertyTypeInEntitySetAclRemovalRequest that = (PropertyTypeInEntitySetAclRemovalRequest) o;

        if ( entitySetName != null ? !entitySetName.equals( that.entitySetName ) : that.entitySetName != null )
            return false;
        return properties != null ? properties.equals( that.properties ) : that.properties == null;
    }

    @Override
    public int hashCode() {
        int result = entitySetName != null ? entitySetName.hashCode() : 0 ;
        result = 31 * result + ( properties != null ? properties.hashCode() : 0 );
        return result;
    }

    public String getName() {
        return entitySetName;
    }

    public PropertyTypeInEntitySetAclRemovalRequest setName( String entitySetName ) {
        this.entitySetName = entitySetName;
        return this;
    }

    public Set<FullQualifiedName> getProperties() {
        return properties;
    }

    public PropertyTypeInEntitySetAclRemovalRequest setProperties( Set<FullQualifiedName> properties ) {
        this.properties = properties;
        return this;
    }
    
    @JsonCreator
    public static PropertyTypeInEntitySetAclRemovalRequest newAclRemovalRequest(
            @JsonProperty( SerializationConstants.NAME_FIELD) String entitySetName,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD) Set<FullQualifiedName> properties) {
        return new PropertyTypeInEntitySetAclRemovalRequest()
                .setName( entitySetName )
                .setProperties( properties );
    }


}
