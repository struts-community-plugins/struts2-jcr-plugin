package org.apache.struts2.jcr.wrapper;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.version.Version;
import javax.jcr.version.VersionIterator;
import javax.jcr.version.VersionHistory;
import javax.jcr.query.RowIterator;
import javax.jcr.query.Row;

/**
 *
 */
public interface WrapperFactory {
  Node createWrapped(Node node);
  Version createWrapped(Version version);
  VersionHistory createWrapped(VersionHistory versionHistory);
  VersionIterator createWrapped(VersionIterator versionIterator);
  Row createWrapped(Row row);
  NodeIterator createWrapped(NodeIterator nodeIterator);
  RowIterator createWrapped(RowIterator rowIterator);
  Property createWrapped(Property property);
}
