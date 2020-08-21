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

package com.openlattice.organization

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
enum class OrganizationEntitySetFlag {
    INTERNAL, // internally hosted data sets, standard, read/write all good
    EXTERNAL, // externally hosted data sets, we should not be writing data through apps to these entity sets
    MATERIALIZED, // materialized to an external datasource
    EDM_UNSYNCHRONIZED, // marker for pending edm changes to materialized entity set
    DATA_UNSYNCHRONIZED, // marker for pending updates to materialized entity set
    MATERIALIZE_PERMISSION_UNSYNCHRONIZED, // marker for pending permission changes on property type
    MATERIALIZE_PERMISSION_REMOVED // materialize permission gets removed from entity set
}