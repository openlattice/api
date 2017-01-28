package com.dataloom.datasource;

import java.util.UUID;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public interface DatasourceApi {

    UUID createDatasource( String name );
    void getDatasource();
    void deleteDatasource();

}
