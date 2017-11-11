package com.dataloom.mapstores;

import com.dataloom.authorization.*;
import com.dataloom.authorization.securable.AbstractSecurableObject;
import com.dataloom.authorization.securable.AbstractSecurableType;
import com.dataloom.authorization.securable.SecurableObjectType;
import com.dataloom.data.EntityKey;
import com.dataloom.edm.EdmDetails;
import com.dataloom.edm.EntitySet;
import com.dataloom.edm.type.*;
import com.dataloom.organization.Organization;
import com.dataloom.organization.roles.Role;
import com.dataloom.organization.roles.RoleKey;
import com.dataloom.requests.PermissionsRequestDetails;
import com.dataloom.requests.Request;
import com.dataloom.requests.RequestStatus;
import com.dataloom.requests.Status;
import com.datastax.driver.core.utils.UUIDs;
import com.google.common.base.Optional;
import com.google.common.collect.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import java.util.*;
import java.util.stream.Collectors;

public final class TestDataFactory {
    private static final SecurableObjectType[] securableObjectTypes = SecurableObjectType.values();
    private static final Permission[]          permissions          = Permission.values();
    private static final Action[]              actions              = Action.values();
    private static final RequestStatus[]       requestStatuses      = RequestStatus.values();
    private static final Analyzer[]            analyzers            = Analyzer.values();
    private static final Random                r                    = new Random();

    private TestDataFactory() {
    }

    public static Principal userPrincipal() {
        return new Principal( PrincipalType.USER, RandomStringUtils.randomAlphanumeric( 10 ) );
    }

    public static Principal rolePrincipal() {
        return new Principal( PrincipalType.ROLE, RandomStringUtils.randomAlphanumeric( 5 ) );
    }

    public static EntityType entityType( PropertyType... keys ) {
        return childEntityType( null, keys );
    }

    public static EntityType childEntityType( UUID parentId, PropertyType... keys ) {
        return childEntityTypeWithPropertyType( parentId,
                ImmutableSet.of( UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() ),
                keys );
    }

    public static EntityType childEntityTypeWithPropertyType(
            UUID parentId,
            Set<UUID> propertyTypes,
            PropertyType... keys ) {
        LinkedHashSet<UUID> k = keys.length > 0
                ? Arrays.asList( keys ).stream().map( PropertyType::getId )
                .collect( Collectors.toCollection( Sets::newLinkedHashSet ) )
                : Sets.newLinkedHashSet( Arrays.asList( UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() ) );
        return new EntityType(
                UUID.randomUUID(),
                fqn(),
                RandomStringUtils.randomAlphanumeric( 5 ),
                Optional.of( RandomStringUtils.randomAlphanumeric( 5 ) ),
                ImmutableSet.of( fqn(), fqn(), fqn() ),
                k,
                Sets.newLinkedHashSet( Sets
                        .union( k, propertyTypes ) ),
                Optional.fromNullable( parentId ),
                Optional.of( SecurableObjectType.EntityType ) );
    }

    public static AssociationType associationType( PropertyType... keys ) {
        EntityType et = entityType( keys );
        return new AssociationType(
                Optional.of( et ),
                Sets.newLinkedHashSet( Arrays.asList( UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() ) ),
                Sets.newLinkedHashSet( Arrays.asList( UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() ) ),
                false );
    }

    public static AssociationType associationTypeWithProperties( Set<UUID> propertyTypes, PropertyType... keys ) {
        if ( propertyTypes.size() == 0 ) { return associationType( keys ); }
        EntityType et = childEntityTypeWithPropertyType( null, propertyTypes, keys );
        UUID ptId = propertyTypes.iterator().next();
        return new AssociationType(
                Optional.of( et ),
                Sets.newLinkedHashSet( Arrays.asList( ptId ) ),
                Sets.newLinkedHashSet( Arrays.asList( ptId ) ),
                false );
    }

    public static FullQualifiedName fqn() {
        return new FullQualifiedName(
                RandomStringUtils.randomAlphanumeric( 5 ),
                RandomStringUtils.randomAlphanumeric( 5 ) );
    }

    public static String email() {
        return RandomStringUtils.randomAlphanumeric( 5 ) + "@" + RandomStringUtils.randomAlphanumeric( 5 ) + ".com";
    }

    public static String name() {
        return RandomStringUtils.randomAlphanumeric( 5 );
    }

    public static EntitySet entitySet() {
        return entitySetWithType( UUID.randomUUID() );
    }

    public static EntitySet entitySetWithType( UUID entityTypeId ) {
        return new EntitySet(
                UUID.randomUUID(),
                entityTypeId,
                RandomStringUtils.randomAlphanumeric( 5 ),
                RandomStringUtils.randomAlphanumeric( 5 ),
                Optional.of( RandomStringUtils.randomAlphanumeric( 5 ) ),
                ImmutableSet.of( email(), email() ) );
    }

    public static PropertyType propertyType() {
        return new PropertyType(
                UUID.randomUUID(),
                fqn(),
                RandomStringUtils.randomAlphanumeric( 5 ),
                Optional.of( RandomStringUtils.randomAlphanumeric( 5 ) ),
                ImmutableSet.of(),
                EdmPrimitiveTypeKind.String,
                Optional.of( r.nextBoolean() ),
                Optional.of( analyzers[ r.nextInt( analyzers.length ) ] ) );
    }

    public static Organization organization() {
        return new Organization(
                Optional.of( UUID.randomUUID() ),
                RandomStringUtils.randomAlphanumeric( 5 ),
                Optional.of( RandomStringUtils.randomAlphanumeric( 5 ) ),
                ImmutableSet.of( RandomStringUtils.randomAlphanumeric( 5 ), RandomStringUtils.randomAlphanumeric( 5 ) ),
                ImmutableSet.of( userPrincipal() ),
                ImmutableSet.of( role() ),
                ImmutableSet.of( UUID.randomUUID() ) );
    }

    public static Role role() {
        return new Role(
                Optional.of( UUID.randomUUID() ),
                UUID.randomUUID(),
                new Principal( PrincipalType.ROLE,
                        UUID.randomUUID().toString().concat( "|" )
                                .concat( RandomStringUtils.randomAlphanumeric( 5 ) ) ),
                RandomStringUtils.randomAlphanumeric( 5 ),
                Optional.of( RandomStringUtils.randomAlphanumeric( 5 ) ) );
    }

    public static SecurableObjectType securableObjectType() {
        return securableObjectTypes[ r.nextInt( securableObjectTypes.length ) ];
    }

    public static EnumSet<Permission> permissions() {
        return Arrays.asList( permissions )
                .stream()
                .filter( elem -> r.nextBoolean() )
                .collect( Collectors.toCollection( () -> EnumSet.noneOf( Permission.class ) ) );
    }

    public static Ace ace() {
        return new Ace( userPrincipal(), permissions() );
    }

    public static Acl acl() {
        return new Acl(
                ImmutableList.of( UUID.randomUUID(), UUID.randomUUID() ),
                ImmutableList.of( ace(), ace(), ace(), ace() ) );
    }

    public static AclData aclData() {
        return new AclData(
                acl(),
                actions[ r.nextInt( actions.length ) ] );
    }

    public static List<UUID> aclKey() {
        return ImmutableList.of( UUID.randomUUID(), UUID.randomUUID() );
    }

    public static EdmDetails edmDetails() {
        Set<PropertyType> pts = ImmutableSet.of( propertyType(), propertyType(), propertyType() );
        Set<EntityType> ets = ImmutableSet.of( entityType(), entityType(), entityType() );
        Set<EntitySet> ess = ImmutableSet.of( entitySet() );
        return new EdmDetails(
                pts.stream().collect( Collectors.toMap( AbstractSecurableType::getId, v -> v ) ),
                ets.stream().collect( Collectors.toMap( AbstractSecurableType::getId, v -> v ) ),
                ess.stream().collect( Collectors.toMap( AbstractSecurableObject::getId, v -> v ) ) );
    }

    public static Map<UUID, EnumSet<Permission>> aclChildPermissions() {
        Map<UUID, EnumSet<Permission>> permissions = new HashMap<>();
        permissions.put( UUID.randomUUID(), EnumSet.of( Permission.READ ) );
        permissions.put( UUID.randomUUID(), EnumSet.of( Permission.WRITE ) );
        permissions.put( UUID.randomUUID(), EnumSet.of( Permission.READ, Permission.WRITE ) );
        return permissions;
    }

    public static PermissionsRequestDetails unresolvedPRDetails() {
        return new PermissionsRequestDetails( aclChildPermissions(), RequestStatus.SUBMITTED );
    }

    public static PermissionsRequestDetails resolvedPRDetails() {
        return new PermissionsRequestDetails( aclChildPermissions(), RequestStatus.APPROVED );
    }

    public static RequestStatus requestStatus() {
        return requestStatuses[ r.nextInt( requestStatuses.length ) ];
    }

    public static Request request() {
        return new Request(
                TestDataFactory.aclKey(),
                TestDataFactory.permissions(),
                Optional.of( "Requesting for this object because RandomStringUtils.randomAlphanumeric( 5 )" ) );
    }

    public static Status status() {
        return new Status(
                request(),
                TestDataFactory.userPrincipal(),
                TestDataFactory.requestStatus() );
    }

    public static Map<String, SetMultimap<UUID, Object>> randomStringEntityData(
            int numberOfEntries,
            Set<UUID> propertyIds ) {
        Map<String, SetMultimap<UUID, Object>> data = new HashMap<>();
        for ( int i = 0; i < numberOfEntries; i++ ) {
            String entityId = UUID.randomUUID().toString();
            SetMultimap<UUID, Object> entity = HashMultimap.create();
            for ( UUID propertyId : propertyIds ) {
                entity.put( propertyId, RandomStringUtils.randomAlphanumeric( 5 ) );
            }

            data.put( entityId, entity );
        }
        return data;
    }

    public static EntityKey entityKey() {
        return entityKey( UUID.randomUUID() );
    }

    public static EntityKey entityKey( UUID entitySetId ) {
        return entityKey( entitySetId, UUIDs.timeBased() );
    }

    public static EntityKey entityKey( UUID entitySetId, UUID syncId ) {
        return new EntityKey( entitySetId, RandomStringUtils.random( 10 ), syncId );
    }

    public static RoleKey roleKey() {
        return new RoleKey( UUID.randomUUID(), UUID.randomUUID() );
    }

    public static ComplexType complexType() {
        return new ComplexType(
                UUID.randomUUID(),
                fqn(),
                RandomStringUtils.randomAlphanumeric( 5 ),
                Optional.of( "test complex type" ),
                ImmutableSet.of( fqn(), fqn() ),
                Sets.newLinkedHashSet( Arrays.asList( UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID() ) ),
                Optional.absent(),
                SecurableObjectType.ComplexType );
    }

    public static EnumType enumType() {
        return new EnumType(
                Optional.of( UUID.randomUUID() ),
                fqn(),
                RandomStringUtils.randomAlphanumeric( 5 ),
                Optional.of( "test enum type" ),
                Sets.newLinkedHashSet( Arrays.asList( "Blue", "Red", "Green" ) ),
                ImmutableSet.of( fqn(), fqn(), fqn() ),
                Optional.of( EdmPrimitiveTypeKind.Int32 ),
                false,
                Optional.of( true ),
                Optional.of( Analyzer.METAPHONE ) );
    }
}