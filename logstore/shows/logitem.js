function(doc, req) {
    var ddoc = this;
    var Mustache = require("lib/mustache");

    var data = {
        header : {},
        title : "Log item",
        timestamp : doc.timestamp,
        reporttype : doc.reportType,
        servername : doc.serverName,
        ndc : doc.ndc,
        reportnumber : doc.reportNumber,
        message : doc.message
    };

    return Mustache.to_html(ddoc.templates.logitem, data, ddoc.templates.partials);
}