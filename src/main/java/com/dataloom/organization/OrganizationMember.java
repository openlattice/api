/*
 * Copyright (C) 2017. OpenLattice, Inc
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

package com.dataloom.organization;

import com.dataloom.client.serialization.SerializationConstants;
import com.dataloom.directory.pojo.Auth0UserBasic;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openlattice.authorization.SecurablePrincipal;
import java.util.Collection;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
public class OrganizationMember {
    private final SecurablePrincipal             principal;
    private final Auth0UserBasic                 profile;
    private final Collection<SecurablePrincipal> roles;

    @JsonCreator
    public OrganizationMember(
            @JsonProperty( SerializationConstants.PRINCIPAL ) SecurablePrincipal principal,
            @JsonProperty( SerializationConstants.PROFILE_FIELD ) Auth0UserBasic profile,
            @JsonProperty( SerializationConstants.ROLES ) Collection<SecurablePrincipal> roles ) {
        this.principal = principal;
        this.profile = profile;
        this.roles = roles;
    }

    @JsonProperty( SerializationConstants.PRINCIPAL )
    public SecurablePrincipal getPrincipal() {
        return principal;
    }

    @JsonProperty( SerializationConstants.PROFILE_FIELD )
    public Auth0UserBasic getProfile() {
        return profile;
    }

    @JsonProperty( SerializationConstants.ROLES )
    public Collection<SecurablePrincipal> getRoles() {
        return roles;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o ) { return true; }
        if ( !( o instanceof OrganizationMember ) ) { return false; }

        OrganizationMember that = (OrganizationMember) o;

        if ( !principal.equals( that.principal ) ) { return false; }
        if ( !profile.equals( that.profile ) ) { return false; }
        return roles.equals( that.roles );
    }

    @Override public int hashCode() {
        int result = principal.hashCode();
        result = 31 * result + profile.hashCode();
        result = 31 * result + roles.hashCode();
        return result;
    }

    @Override public String toString() {
        return "OrganizationMember{" +
                "principal=" + principal +
                ", profile=" + profile +
                ", roles=" + roles +
                '}';
    }
}
