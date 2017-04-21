package com.dataloom.edm.internal;

import java.util.UUID;

public final class DatastoreConstants {

    private DatastoreConstants() {}

    /**
     * This property is deprecated and should be dynamically configured.
     */
    @Deprecated
    public static final String KEYSPACE                = "sparks";

    // TABLES
    public static final String ENTITY_SETS_TABLE       = "entity_sets";
    public static final String CONTAINERS_TABLE        = "containers";
    public static final String COUNT_FIELD             = "count";
    public static final String ENTITY_TYPES_TABLE      = "entity_types";
    public static final String PROPERTY_TYPES_TABLE    = "property_types";

    // PARAMETERS
    public static final String APPLIED_FIELD           = "[applied]";

    // DEFAULT
    public static final UUID   DEFAULT_ORGANIZATION_ID = new UUID( 0, 0 );

}
