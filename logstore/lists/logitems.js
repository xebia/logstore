function(head, req) {
    var ddoc = this;
    var Mustache = require("lib/mustache");
    var List = require("vendor/couchapp/lib/list");
    var path = require("vendor/couchapp/lib/path").init(req);

    var path_parts = req.path;
    // The provides function serves the format the client requests.
    // The first matching format is sent, so reordering functions changes
    // thier priority. In this case HTML is the preferred format, so it comes first.
    provides("html", function() {
        var key = "";
        // render the html head using a template
        var stash = {
            header : {},
            db : req.path[0],
            design : req.path[2],
            title : "Log items",
            logitems : List.withRows(function(row) {
                var logitem = row.value;
                key = row.key;
                return {
                    timestamp : logitem.timestamp,
                    reporttype : logitem.reportType,
                    servername : logitem.serverName,
                    ndc : logitem.ndc,
                    reportnumber : logitem.reportNumber,
                    message : logitem.message,
                    link : path.show('logitem', row.id)
                };
            }),
            older : function() {
                return path.older(key);
            },
            "5" : path.limit(5),
            "10" : path.limit(10),
            "25" : path.limit(25)
        };
        return Mustache.to_html(ddoc.templates.logitems, stash, ddoc.templates.partials, List.send);
    });
}