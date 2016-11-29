package com.dataloom.directory.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Auth0UserBasic {
    public static final String USER_ID_FIELD  = "user_id";
    public static final String EMAIL_FIELD    = "email";
    public static final String NICKNAME_FIELD = "nickname";
    public static final String USERNAME_FIELD = "username";
    public static final String ROLES_FIELD    = "roles";

    private final String       userId;
    private final String       email;
    private final String       nickname;
    private final String       username;
    private final List<String> roles;

    @JsonCreator
    public Auth0UserBasic(
            @JsonProperty( USER_ID_FIELD ) String userId,
            @JsonProperty( EMAIL_FIELD ) String email,
            @JsonProperty( NICKNAME_FIELD ) String nickname,
            @JsonProperty( ROLES_FIELD ) List<String> roles ) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.roles = roles;
        if ( this.email != null ) {
            this.username = this.email;
        } else if ( this.nickname != null ) {
            this.username = this.nickname;
        } else {
            this.username = this.userId;
        }
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

    @JsonProperty( ROLES_FIELD )
    public List<String> getRoles() {
        return roles;
    }
}
