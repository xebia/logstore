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

/**
 *
 */
public class LogImportSequential {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogImportSequential.class);

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

            LogItem logItem;
            while ((logItem = reader.readNext()) != null) {
                LOGGER.debug("Read new log item: {}", logItem);
                template.create(logItem);
                LOGGER.debug("Log item created. Id: {}, revision: {}", logItem.getId(), logItem.getRevision());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileReader);
        }
    }

    public static void main(String[] args) {
        LogImportSequential logImportSequential = new LogImportSequential();
        logImportSequential.execute(args[0]);
    }
}
