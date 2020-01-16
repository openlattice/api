/*
 * Copyright (C) 2019. OpenLattice, Inc.
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
 *
 */
package com.openlattice.data

import com.fasterxml.jackson.annotation.JsonValue
import com.openlattice.edm.EdmConstants
import org.apache.commons.lang3.NotImplementedException
import java.util.LinkedHashSet

/*
 * Note: T must have a good .toString() method, as this will be used for serialization
 */
class EntitySetData<T>(
        val columnTitles: LinkedHashSet<String>,
        private val entities: Iterable<Map<T, Set<Any>>>
) : Iterable<Map<T, Set<Any>>> {
    init {
        this.columnTitles.add(EdmConstants.ID_FQN.fullQualifiedNameAsString)
    }

    @JsonValue
    fun getEntities(): Iterable<Map<T, Set<Any>>> {
        return Iterable(entities::iterator)
    }

    override fun iterator(): Iterator<Map<T, Set<Any>>> {
        throw NotImplementedException("Purposefully not implemented.")
    }
}