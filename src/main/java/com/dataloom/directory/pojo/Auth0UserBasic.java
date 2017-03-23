package com.dataloom.directory.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;

import java.util.*;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Auth0UserBasic {
    public static final String USER_ID_FIELD       = "user_id";
    public static final String EMAIL_FIELD         = "email";
    public static final String NICKNAME_FIELD      = "nickname";
    public static final String USERNAME_FIELD      = "username";
    public static final String APP_METADATA_FIELD  = "app_metadata";
    public static final String ROLES_FIELD         = "roles";
    public static final String ORGANIZATIONS_FIELD = "organization";

    private final String      userId;
    private final String      email;
    private final String      nickname;
    private final String      username;
    private final Set<String> roles;
    private final Set<String> organizations;

    @SuppressWarnings( "unchecked" )
    @JsonCreator
    @JsonIgnoreProperties(
            ignoreUnknown = true )
    public Auth0UserBasic(
            @JsonProperty( USER_ID_FIELD ) String userId,
            @JsonProperty( EMAIL_FIELD ) String email,
            @JsonProperty( NICKNAME_FIELD ) String nickname,
            @JsonProperty( APP_METADATA_FIELD ) Map<String, Object> appMetadata ) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        if ( this.email != null ) {
            this.username = this.email;
        } else if ( this.nickname != null ) {
            this.username = this.nickname;
        } else {
            this.username = this.userId;
        }
        this.roles = Sets.newHashSet(
                ( (List<String>) MoreObjects.firstNonNull( appMetadata, new HashMap<>() ).getOrDefault( "roles",
                        new ArrayList<String>() ) ) );
        this.organizations = Sets
                .newHashSet( ( (List<String>) MoreObjects.firstNonNull( appMetadata, new HashMap<>() ).getOrDefault(
                        "organizations",
                        new ArrayList<String>() ) ) );
    }

    @JsonProperty( USER_ID_FIELD )
    public String getUserId() {
        return userId;
    }

    @JsonProperty( EMAIL_FIELD )
    public String getEmail() {
        return email;
    }

    @JsonProperty( NICKNAME_FIELD )
    public String getNickname() {
        return nickname;
    }

    @JsonProperty( USERNAME_FIELD )
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public Set<String> getRoles() {
        return Collections.unmodifiableSet( roles );
    }

    @JsonProperty( ORGANIZATIONS_FIELD )
    public Set<String> getOrganizations() {
        return Collections.unmodifiableSet( organizations );
    }

    @Override public String toString() {
        return "Auth0UserBasic{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                ", organizations=" + organizations +
                '}';
    }

    @Override
    public int hashCode() {
        //user id is a unique identifier.
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( userId == null ) ? 0 : userId.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        //user id is a unique identifier.
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Auth0UserBasic other = (Auth0UserBasic) obj;
        if ( userId == null ) {
            if ( other.userId != null ) return false;
        } else if ( !userId.equals( other.userId ) ) return false;
        return true;
    }
    
    
}
