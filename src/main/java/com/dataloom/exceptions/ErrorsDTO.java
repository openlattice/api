/*
 * Copyright (C) 2017. Kryptnostic, Inc (dba Loom)
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
 * You can contact the owner of the copyright at support@thedataloom.com
 */

package com.dataloom.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.dataloom.client.serialization.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorsDTO {
    private List<ErrorDTO> errors;

    public ErrorsDTO() {
        this.errors = new ArrayList<ErrorDTO>();
    }

    public ErrorsDTO( LoomExceptions error, String message ) {
        this();
        this.addError( error, message );
    }

    public void addError( LoomExceptions error, String message ) {
        errors.add( new ErrorDTO( error, message ) );
    }

    @JsonProperty( SerializationConstants.ERRORS )
    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors( List<ErrorDTO> errors ) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorsDTO [errors=" + errors + "]";
    }

    @JsonIgnore
    public boolean isEmpty() {
        return errors.isEmpty();
    }
}
