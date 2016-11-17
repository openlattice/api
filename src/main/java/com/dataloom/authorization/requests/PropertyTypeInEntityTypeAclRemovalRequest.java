package com.dataloom.authorization.requests;

import java.util.Set;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyTypeInEntityTypeAclRemovalRequest {
    
    @JsonProperty( SerializationConstants.TYPE_FIELD)
    protected FullQualifiedName entityTypeFqn;
    @JsonProperty( SerializationConstants.PROPERTIES_FIELD)
    protected Set<FullQualifiedName> properties;
    
    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        PropertyTypeInEntityTypeAclRemovalRequest that = (PropertyTypeInEntityTypeAclRemovalRequest) o;

        if ( entityTypeFqn != null ? !entityTypeFqn.equals( that.entityTypeFqn ) : that.entityTypeFqn != null )
            return false;
        return properties != null ? properties.equals( that.properties ) : that.properties == null;
    }

    @Override
    public int hashCode() {
        int result = entityTypeFqn != null ? entityTypeFqn.hashCode() : 0;
        result = 31 * result + ( properties != null ? properties.hashCode() : 0 );
        return result;
    }

    public FullQualifiedName getType() {
        return entityTypeFqn;
    }

    public PropertyTypeInEntityTypeAclRemovalRequest setType( FullQualifiedName entityTypeFqn ) {
        this.entityTypeFqn = entityTypeFqn;
        return this;
    }

    public Set<FullQualifiedName> getProperties() {
        return properties;
    }

    public PropertyTypeInEntityTypeAclRemovalRequest setProperties( Set<FullQualifiedName> properties ) {
        this.properties = properties;
        return this;
    }
    
    @JsonCreator
    public static PropertyTypeInEntityTypeAclRemovalRequest newAclRemovalRequest(
            @JsonProperty( SerializationConstants.TYPE_FIELD) FullQualifiedName entityTypeFqn,
            @JsonProperty( SerializationConstants.PROPERTIES_FIELD) Set<FullQualifiedName> properties) {
        return new PropertyTypeInEntityTypeAclRemovalRequest()
                .setType( entityTypeFqn )
                .setProperties( properties );
    }

}
