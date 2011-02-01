/*
 * Copyright 2011, Xebia BV, The Netherlands
 *
 * info@xebia.com
 */
package nl.xebia.appincubator.logstore;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import java.util.List;

/**
 *
 */
public class CouchDbDatabaseTemplate {

    private final CouchDbConnector database;

    public CouchDbDatabaseTemplate(HttpClient httpClient, String databaseName) {
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        database = new StdCouchDbConnector(databaseName, dbInstance);
        database.createDatabaseIfNotExists();
    }

    public void executeBulk(List<?> items) {
        database.executeBulk(items);
    }

    public void create(Object object) {
        database.create(object);
    }
}
