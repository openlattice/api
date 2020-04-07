package com.openlattice.aws

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
const val REGION_NAME = "OL_BUILD_REGION_NAME"
const val PROFILE_NAME = "OL_BUILD_PROFILE_NAME"
const val BUCKET_NAME = "OL_BUILD_BUCKET"

fun getBuildRegion(): String = System.getenv(REGION_NAME) ?: "us-gov-west-1"


fun getBuildProfile(): String = System.getenv(PROFILE_NAME) ?: "openlattice_builds"


fun getBuildBucket(): String = requireNotNull(
        System.getenv(BUCKET_NAME)
) { "Build configuration bucket must be specified in OL_BUILD_BUCKET environment variable." }
