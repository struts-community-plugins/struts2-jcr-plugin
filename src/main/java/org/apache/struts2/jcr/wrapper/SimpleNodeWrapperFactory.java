package org.apache.struts2.jcr.wrapper;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.query.Row;
import javax.jcr.query.RowIterator;
import javax.jcr.version.Version;
import javax.jcr.version.VersionIterator;
import javax.jcr.version.VersionHistory;

/**
 * Instance of WrapperFactory for wraping JCR objects.  
 */
public class SimpleNodeWrapperFactory implements WrapperFactory {
  public NodeWrapper createWrapped(Node node) {
    if(node==null){
      return null;
    }
    return (NodeWrapper) (node instanceof NodeWrapperImpl ? node : new NodeWrapperImpl(node, this));
  }

  public Version createWrapped(Version version) {
    if(version==null){
      return null;
    }
    return version instanceof VersionWrapperImpl ? version : new VersionWrapperImpl(version, this);
  }

  public VersionHistory createWrapped(VersionHistory versionHistory) {
    if(versionHistory==null){
      return null;
    }
    return versionHistory instanceof VersionHistoryWrapperImpl ? versionHistory : new VersionHistoryWrapperImpl(versionHistory, this);
  }

  public VersionIterator createWrapped(VersionIterator versionIterator) {
    if(versionIterator==null){
      return null;
    }
    return versionIterator instanceof VersionIteratorWrapperImpl ? versionIterator : new VersionIteratorWrapperImpl(versionIterator, this);
  }

  public Row createWrapped(Row row) {
    if(row==null){
      return null;
    }
    return (row instanceof RowWrapperImpl ? row : new RowWrapperImpl(row));
  }

  public NodeIterator createWrapped(NodeIterator nodeIterator) {
    if(nodeIterator==null){
      return null;
    }
    return nodeIterator instanceof NodeIteratorWrapperImpl ? nodeIterator : new NodeIteratorWrapperImpl(nodeIterator, this);
  }

  public RowIterator createWrapped(RowIterator rowIterator) {
    if(rowIterator==null){
      return null;
    }
    return rowIterator instanceof RowIteratorWrapperImpl ? rowIterator : new RowIteratorWrapperImpl(rowIterator, this);
  }

  public Property createWrapped(Property property) {
    if(property==null){
      return null;
    }
    return property instanceof PropertyWrapperImpl ? property : new PropertyWrapperImpl(property, this);
  }
}
