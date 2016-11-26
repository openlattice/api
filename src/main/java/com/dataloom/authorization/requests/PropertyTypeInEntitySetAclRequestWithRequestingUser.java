package com.dataloom.authorization.requests;

import com.dataloom.data.SerializationConstants;
import com.dataloom.edm.requests.PropertyTypeInEntitySetAclRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyTypeInEntitySetAclRequestWithRequestingUser {

    @JsonProperty( SerializationConstants.REQUESTING_USER)
    private String              requestingUser;

    @JsonProperty( SerializationConstants.REQUEST )
    private PropertyTypeInEntitySetAclRequest request;

    public String getRequestingUser() {
        return requestingUser;
    }

    public PropertyTypeInEntitySetAclRequestWithRequestingUser setRequestingUser( String requestingUser ) {
        this.requestingUser = requestingUser;
        return this;
    }

    public PropertyTypeInEntitySetAclRequest getRequest() {
        return request;
    }

    public PropertyTypeInEntitySetAclRequestWithRequestingUser setRequest( PropertyTypeInEntitySetAclRequest request ) {
        this.request = request;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( request == null ) ? 0 : request.hashCode() );
        result = prime * result + ( ( requestingUser == null ) ? 0 : requestingUser.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null || getClass() != obj.getClass() )
            return false;

        PropertyTypeInEntitySetAclRequestWithRequestingUser that = (PropertyTypeInEntitySetAclRequestWithRequestingUser) obj;

        if ( requestingUser != null ? !requestingUser.equals( that.requestingUser ) : that.requestingUser != null )
            return false;
        return request != null ? request.equals( that.request ) : that.request == null;
    }

    @Override
    public String toString() {
        return "PropertyTypeInEntitySetAclRequestWithRequestingUser [requestingUser=" + requestingUser + ", request="
                + request + "]";
    }
}
