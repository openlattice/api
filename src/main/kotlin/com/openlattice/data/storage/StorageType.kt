package com.openlattice.data.storage

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
enum class Datastore {
    DEFAULT, //For frequently updated or queried data, with a high hit ratio. Expensive storage, but free reads. 
    OBJECT   //For infrequently updated or queried data, with a low hit ratio. Cheaper eventually consistent storage
}

