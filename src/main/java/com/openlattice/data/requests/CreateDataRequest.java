package com.openlattice.data.requests;

import com.dataloom.mappers.ObjectMappers;
import com.esotericsoftware.kryo.Kryo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.openlattice.client.serialization.SerializationConstants;
import com.openlattice.data.serializers.FullQualifiedNameJacksonDeserializer;
import com.openlattice.data.serializers.FullQualifiedNameJacksonSerializer;
import de.javakaffee.kryoserializers.UUIDSerializer;
import de.javakaffee.kryoserializers.guava.HashMultimapSerializer;
import de.javakaffee.kryoserializers.guava.ImmutableMultimapSerializer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CreateDataRequest implements Serializable {

    private static final ObjectMapper      mapper          = ObjectMappers.getSmileMapper();
    private static final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial( () -> {

        Kryo kryo = new Kryo();
        kryo.register( UUID.class, new UUIDSerializer() );
        HashMultimapSerializer.registerSerializers( kryo );
        ImmutableMultimapSerializer.registerSerializers( kryo );
        return kryo;
    } );

    static {
        FullQualifiedNameJacksonSerializer.registerWithMapper( ObjectMappers.getJsonMapper() );
        FullQualifiedNameJacksonDeserializer.registerWithMapper( ObjectMappers.getJsonMapper() );
    }

    private Set<UUID>               entitySetIds;
    private Set<EntityRequest>      entities;
    private Set<AssociationRequest> associations;

    @JsonCreator
    public CreateDataRequest(
            @JsonProperty( SerializationConstants.ENTITY_SET_IDS ) Set<UUID> entitySetIds,
            @JsonProperty( SerializationConstants.ENTITIES ) Set<EntityRequest> entities,
            @JsonProperty( SerializationConstants.ASSOCIATIONS ) Set<AssociationRequest> associations ) {
        this.entitySetIds = entitySetIds;
        this.entities = entities;
        this.associations = associations;
    }

    @JsonProperty( SerializationConstants.ENTITY_SET_IDS )
    public Set<UUID> getEntitySetIds() {
        return entitySetIds;
    }

    @JsonProperty( SerializationConstants.ENTITIES )
    public Set<EntityRequest> getEntities() {
        return entities;
    }

    @JsonProperty( SerializationConstants.ASSOCIATIONS )
    public Set<AssociationRequest> getAssociations() {
        return associations;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        CreateDataRequest that = (CreateDataRequest) o;

        if ( entitySetIds != null ? !entitySetIds.equals( that.entitySetIds ) : that.entitySetIds != null )
            return false;
        if ( entities != null ? !entities.equals( that.entities ) : that.entities != null )
            return false;
        return associations != null ? associations.equals( that.associations ) : that.associations == null;
    }

    @Override
    public int hashCode() {
        int result = entitySetIds != null ? entitySetIds.hashCode() : 0;
        result = 31 * result + ( entities != null ? entities.hashCode() : 0 );
        result = 31 * result + ( associations != null ? associations.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        return "CreateDataRequest{" +
                "entitySetIds=" + entitySetIds +
                ", entities=" + entities +
                ", associations=" + associations +
                '}';
    }

    private void writeObject( ObjectOutputStream oos )
            throws IOException {
        oos.writeInt( entitySetIds.size() );

        for ( UUID entitySetId : entitySetIds ) {
            CreateDataRequest.serialize( oos, entitySetId );
        }

        oos.writeInt( entities.size() );
        for ( EntityRequest entity : entities ) {
            serialize( oos, entity.getEntityKeyId() );
            serialize( oos, entity.getDetails() );
        }

        oos.writeInt( associations.size() );
        for ( AssociationRequest association : associations ) {
            serialize( oos, association.getSrc() );
            serialize( oos, association.getDst() );
            serialize( oos, association.getEntityKeyId() );
            serialize( oos, association.getDetails() );
        }

    }

    private void readObject( ObjectInputStream ois ) throws IOException, ClassNotFoundException {
        int entitySetIdsCount = ois.readInt();
        entitySetIds = new HashSet<>();
        entities = new HashSet<>();
        associations = new HashSet<>();

        for ( int i = 0; i < entitySetIdsCount; ++i ) {
            entitySetIds.add( deserializeUUID( ois ) );
        }

        int entityCount = ois.readInt();
        for ( int i = 0; i < entityCount; ++i ) {
            UUID entityKeyId = deserializeUUID( ois );
            SetMultimap<UUID, Object> details = deserializeEntityDetails( ois );
            entities.add( new EntityRequest( entityKeyId, details ) );
        }

        int associationCount = ois.readInt();
        for ( int i = 0; i < associationCount; ++i ) {
            UUID src = deserializeUUID( ois );
            UUID dst = deserializeUUID( ois );
            UUID key = deserializeUUID( ois );
            SetMultimap<UUID, Object> details = deserializeEntityDetails( ois );
            associations.add( new AssociationRequest( key, src, dst, details ) );
        }
    }

    private static void serialize( ObjectOutputStream oos, UUID id ) throws IOException {
        oos.writeLong( id.getLeastSignificantBits() );
        oos.writeLong( id.getMostSignificantBits() );
    }

    private static UUID deserializeUUID( ObjectInputStream ois ) throws IOException {
        long lsb = ois.readLong();
        long msb = ois.readLong();
        return new UUID( msb, lsb );
    }

    private static SetMultimap<UUID, Object> deserializeEntityDetails( ObjectInputStream ois ) throws IOException {
        int detailCount = ois.readInt();
        SetMultimap<UUID, Object> details = HashMultimap.create();
        for ( int i = 0; i < detailCount; ++i ) {
            UUID propertyId = deserializeUUID( ois );
            Object detail = null;
            try {
                detail = ois.readObject();
            } catch ( ClassNotFoundException e ) {
                throw new IOException( "Unable to locate class", e );
            }
            details.put( propertyId, detail );
        }
        return details;
    }

    private static void serialize( ObjectOutputStream oos, SetMultimap<UUID, Object> details ) throws IOException {
        oos.writeInt( details.size() );
        for ( Map.Entry<UUID, Object> entry : details.entries() ) {
            serialize( oos, entry.getKey() );
            oos.writeObject( entry.getValue() );
        }
    }

}
