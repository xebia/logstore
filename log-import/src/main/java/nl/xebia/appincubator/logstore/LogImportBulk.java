/*
 * Copyright 2011, Xebia BV, The Netherlands
 *
 * info@xebia.com
 */
package nl.xebia.appincubator.logstore;

import org.apache.commons.io.IOUtils;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class LogImportBulk {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogImportBulk.class);

    private void execute(String fileName) {
        HttpClient httpClient = new StdHttpClient.Builder()
                .host("localhost")
                .port(5984)
                .build();

        CouchDbDatabaseTemplate template = new CouchDbDatabaseTemplate(httpClient, "logstore");
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(fileName);
            LogItemReader reader = new LogItemReader(fileReader);
            List<LogItem> logItems = reader.readAll();
            LOGGER.debug("Executing bulk insert with {} items", logItems.size());
            template.executeBulk(logItems);
            LOGGER.debug("Executed bulk insert");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileReader);
        }
    }

    public static void main(String[] args) {
        LogImportBulk logImportBulk = new LogImportBulk();
        logImportBulk.execute(args[0]);
    }
}
