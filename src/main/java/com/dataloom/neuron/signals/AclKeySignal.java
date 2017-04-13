package com.dataloom.neuron.signals;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dataloom.neuron.NeuronSignal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public class AclKeySignal extends Signal {

    private static final Logger logger = LoggerFactory.getLogger( AclKeySignal.class );

    private List<UUID> aclKey;

    public AclKeySignal( List<UUID> aclKey, NeuronSignal type ) {

        this( aclKey, type, Optional.absent() );
    }

    public AclKeySignal( List<UUID> aclKey, NeuronSignal type, Optional<Object> payload ) {

        super( type, payload );
        this.aclKey = checkNotNull( aclKey );
    }

    @JsonIgnore
    public List<UUID> getAclKey() {
        return this.aclKey;
    }
}
