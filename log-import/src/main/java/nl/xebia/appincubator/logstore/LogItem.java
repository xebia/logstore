/*
 * Copyright 2011, Xebia BV, The Netherlands
 *
 * info@xebia.com
 */
package nl.xebia.appincubator.logstore;

import org.apache.commons.lang.builder.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 */
@JsonIgnoreProperties({"id","revision"})
public class LogItem {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_rev")
    private String revision;

    private String timestamp;

    private String reportType;

    private String serverName;

    private String ndc;

    private String reportNumber;

    private String message;

    public LogItem(String id, String timestamp, String reportType, String serverName, String ndc, String reportNumber, String message) {
        this.id = id;
        this.timestamp = timestamp;
        this.reportType = reportType;
        this.serverName = serverName;
        this.ndc = ndc;
        this.reportNumber = reportNumber;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRevision() {
        return revision;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
                append("id", id).
                append("revision", revision).
                append("timestamp", timestamp).
                append("reportType", reportType).
                append("serverName", serverName).
                append("ndc", ndc).
                append("reportNumber", reportNumber).
                append("message", message).
                toString();
    }
}
