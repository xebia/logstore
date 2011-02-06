function(doc) {
  if (doc.reportNumber) {
    emit(doc.reportNumber, doc);
  }
};