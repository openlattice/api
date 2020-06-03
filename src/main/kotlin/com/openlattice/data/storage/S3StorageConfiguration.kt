package com.openlattice.data.storage

import com.kryptnostic.rhizome.configuration.annotation.ReloadableConfiguration

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
@ReloadableConfiguration(uri = "s3storage.yaml")
data class S3StorageConfiguration(
        val bucket: String,
        val region: String,
        val accessKeyId: String,
        val secretAccessKey: String,
        val threads: Int = 8
) : StorageConfiguration