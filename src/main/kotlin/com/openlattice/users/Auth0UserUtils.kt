package com.openlattice.users

import com.auth0.json.mgmt.users.User
import com.openlattice.authorization.Principal
import com.openlattice.authorization.PrincipalType

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */

fun getPrincipal(user: User): Principal {
    return Principal(PrincipalType.USER, user.id)
}

/**
 * @param user The user for which to read the identities.
 * @return A map of providers to connections for the specified user.
 */
fun getConnections(user: User): Map<String, String> {
    return user.identities.associateBy({ it.provider }, { it.connection })
}

fun getAppMetadata(user: User): Map<String, Set<String>> {
    return user.appMetadata.mapValues { (_, v) ->
        when (v) {
            is String -> listOf(v)
            else -> v as Collection<String>
        }.toSet()
    }
}
