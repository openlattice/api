package com.dataloom.linking;

import java.util.Set;
import java.util.UUID;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class LinkingApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE    = "/datastore";
    String CONTROLLER = "/linking";
    String BASE       = SERVICE + CONTROLLER;

    Void createLink( UUID syncId, UUID linkId, Set<String> linkedEntities );

    Void removeLink( UUID syncId, UUID linkId, Set<String> linkedEntities );

    Set<String> getLink( UUID syncId, String entityId );

    Set<String> getLinkedEntities( UUID syncId, String entityId );

}
