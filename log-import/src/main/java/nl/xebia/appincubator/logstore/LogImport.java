/*
 * Copyright 2011, Xebia BV, The Netherlands
 *
 * info@xebia.com
 */
package nl.xebia.appincubator.logstore;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

/**
 *
 */
public class LogImport {
    public static void main(String[] args) {
        LogImport logImport = new LogImport();
        logImport.execute(args[0]);
    }

    private void execute(String fileName) {
        HttpClient httpClient = new StdHttpClient.Builder().host("127.0.0.1").port(5984).build();

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector db = new StdCouchDbConnector("logstore", dbInstance);

        db.createDatabaseIfNotExists();

        System.out.println(db.getDbInfo());

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = br.readLine()) != null) {
                String[] items = line.split(";");
                LogItem item = new LogItem(UUID.randomUUID().toString(), items[0], items[1], items[2], items[3], items[4], items[5]);
                db.create(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
