package com.dataloom.apps;

import com.dataloom.organization.Organization;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface AppApi {

    String SERVICE    = "/datastore";
    String CONTROLLER = "/app";
    String BASE       = SERVICE + CONTROLLER;

    String INSTALL_PATH = "/install";
    String TYPE_PATH    = "/type";
    String CONFIG_PATH  = "/config";
    String BULK_PATH    = "/bulk";

    String ID              = "id";
    String ORGANIZATION_ID = "organizationId";
    String PREFIX          = "prefix";
    String NAME            = "name";

    String ID_PATH              = "/{" + ID + "}";
    String ORGANIZATION_ID_PATH = "/{" + ORGANIZATION_ID + "}";
    String PREFIX_PATH          = "/{" + PREFIX + "}";
    String NAME_PATH            = "/{" + NAME + "}";

    @GET( BASE )
    Iterable<App> getApps();

    @POST( BASE )
    UUID createApp( @Body App app );

    @POST( BASE + TYPE_PATH )
    UUID createAppType( @Body AppType appType );

    @GET( BASE + ID_PATH )
    App getApp( @Path( ID ) UUID id );

    @GET( BASE + TYPE_PATH + ID_PATH )
    AppType getAppType( @Path( ID ) UUID id );

    @POST( BASE + TYPE_PATH + BULK_PATH )
    Map<UUID, AppType> getAppTypes( @Body Set<UUID> appTypeIds );

    @DELETE( BASE + ID_PATH )
    void deleteApp( @Path( ID ) UUID id );

    @DELETE( BASE + TYPE_PATH + ID_PATH )
    void deleteAppType( @Path( ID ) UUID id );

    @GET( BASE + INSTALL_PATH + ID_PATH + ORGANIZATION_ID_PATH + PREFIX_PATH )
    void installApp(
            @Path( ID ) UUID appId,
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( PREFIX ) String prefix );

    @GET( BASE + CONFIG_PATH + ID_PATH )
    List<AppConfig> getAvailableAppConfigs( @Path( ID ) UUID appId );

}
