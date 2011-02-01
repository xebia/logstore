/*
 * Copyright 2011, Xebia BV, The Netherlands
 *
 * info@xebia.com
 */
package nl.xebia.appincubator.logstore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 */
public class LogItemReader {

    private final BufferedReader bufferedReader;

    public LogItemReader(Reader reader) {
        bufferedReader = new BufferedReader(reader);
    }

    public List<LogItem> readAll() throws IOException {
        List<LogItem> logItems = new ArrayList<LogItem>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            LogItem item = createLogItem(line);
            logItems.add(item);
        }
        return logItems;
    }

    private LogItem createLogItem(String line) {
        String[] items = line.split(";");
        return new LogItem(UUID.randomUUID().toString(), items[0], items[1], items[2], items[3], items[4], items[5]);
    }

    public LogItem readNext() throws IOException {
        String line = bufferedReader.readLine();

        if (line != null) {
            return createLogItem(line);
        }

        return null;
    }
}
