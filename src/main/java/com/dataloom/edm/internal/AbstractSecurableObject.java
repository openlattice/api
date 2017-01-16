package com.dataloom.edm.internal;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.dataloom.authorization.SecurableObjectType;
import com.dataloom.data.SerializationConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 *
 */
public abstract class AbstractSecurableObject implements Serializable {
    private static final long serialVersionUID = -2268620545866476451L;
    protected final UUID      id;
    protected final String    title;
    protected final String    description;
    private final boolean     idPresent;
    private transient int     h                = 0;

    /**
     * @param id The UUID of the securable object. Must not be null.
     * @param title The title of the securable object. Must not be blank.
     * @param description An optional description for the object. Can be blank or null.
     */
    protected AbstractSecurableObject(
            UUID id,
            String title,
            Optional<String> description ) {
        this( Optional.of( id ), title, description );
    }

    /**
     * @param id An optional id for the securable object in the form a UUID.
     * @param title The title of the securable object. Must not be blank.
     * @param description An optional description for the object. Can be blank or null.
     */
    protected AbstractSecurableObject(
            Optional<UUID> id,
            String title,
            Optional<String> description ) {
        this( id.or( UUID::randomUUID ), title, description, id.isPresent() );
    }

    /**
     * @param id The id of the securable object in the form of a UUID. Must not be null.
     * @param title The title of the securable object. Must not be blank.
     * @param description An optional description for the object. Can be blank or null.
     * @param idPresent Whether the id was present at creation time or whether it was generate randomly.
     */
    private AbstractSecurableObject(
            UUID id,
            String title,
            Optional<String> description,
            boolean idPresent ) {

        /*
         * There is no logical requirement that the title not be blank, it would just be very confusing to have a bunch
         * of organizations with no title whatsoever. This can be relaxed in the future.
         */
        checkArgument( StringUtils.isNotBlank( title ), "Title cannot be blank." );
        this.id = checkNotNull( id );
        this.idPresent = idPresent;
        this.description = description.or( "" );
        this.title = title;
    }

    @JsonProperty( SerializationConstants.ID_FIELD )
    public UUID getId() {
        return id;
    }

    @JsonProperty( SerializationConstants.TITLE_FIELD )
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "AbstractSecurableObject [id=" + id + ", title=" + title + ", description=" + description
                + ", idPresent=" + idPresent + "]";
    }

    @JsonProperty( SerializationConstants.DESCRIPTION_FIELD )
    public String getDescription() {
        return description;
    }

    @JsonIgnore
    public boolean wasIdPresent() {
        return idPresent;
    }

    @JsonIgnore
    public abstract SecurableObjectType getCategory();

    @Override
    public int hashCode() {
        if ( h == 0 ) {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
            result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
            result = prime * result + ( idPresent ? 1231 : 1237 );
            result = prime * result + ( ( title == null ) ? 0 : title.hashCode() );
            h = result;
        }
        return h;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof AbstractSecurableObject ) ) {
            return false;
        }
        AbstractSecurableObject other = (AbstractSecurableObject) obj;
        if ( id == null ) {
            if ( other.id != null ) {
                return false;
            }
        } else if ( !id.equals( other.id ) ) {
            return false;
        }
        if ( description == null ) {
            if ( other.description != null ) {
                return false;
            }
        } else if ( !description.equals( other.description ) ) {
            return false;
        }
        if ( title == null ) {
            if ( other.title != null ) {
                return false;
            }
        } else if ( !title.equals( other.title ) ) {
            return false;
        }
        return true;
    }

}
