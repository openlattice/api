package com.openlattice.data

import com.openlattice.data.storage.StorageConfiguration


// @formatter:off
const val SERVICE = "/datastore"
const val CONTROLLER = "/storage-management"
const val BASE = SERVICE + CONTROLLER
// @formatter:on

const val RELOAD_CACHE = "/reload/cache"
const val PRINCIPALS = "/principals"
const val SQL = "/sql"
const val LINKING = "linking"
const val OMIT_ENTITY_SET_ID = "omitEntitySetId"
const val ENTITY_SETS = "/entity/sets"
const val COUNT = "/count"
const val PHONE = "/phone"
const val ORGANIZATION = "/organization"
const val USAGE = "/usage"

const val ID = "id"
const val ID_PATH = "/{$ID}"
const val NAME = "name"
const val NAME_PATH = "/{$NAME}"

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
interface StorageManagementApi {
    fun listConfiguredStorage() : Collection<StorageConfiguration>
}