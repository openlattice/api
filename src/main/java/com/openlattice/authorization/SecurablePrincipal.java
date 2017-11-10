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

package com.openlattice.authorization;

import com.dataloom.authorization.Principal;
import com.dataloom.authorization.PrincipalType;
import com.dataloom.authorization.securable.AbstractSecurableObject;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.UUID;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
public class SecurablePrincipal extends AbstractSecurableObject {
    private final List<UUID> aclKey;
    private final Principal  principal;

    @JsonCreator
    public SecurablePrincipal(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.PRINCIPAL ) Principal principal,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description ) {

        super( id.or( UUID::randomUUID ), title, description );
        this.principal = principal;
        this.aclKey = buildAclKey();
    }

    public Principal getPrincipal() {
        return principal;
    }

    @Override
    @JsonIgnore
    public SecurableObjectType getCategory() {
        return SecurableObjectType.PRINCIPAL;
    }

    @JsonIgnore
    public PrincipalType getPrincipalType() {
        return principal.getType();
    }

    @JsonIgnore
    public String getName() {
        return principal.getId();
    }

    public List<UUID> getAclKey() {
        return aclKey;
    }

    protected List<UUID> buildAclKey() {
        return ImmutableList.of( id );
    }

    @Override public String toString() {
        return "SecurablePrincipal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", principal=" + principal +
                '}';
    }
}
