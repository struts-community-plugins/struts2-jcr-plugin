package org.apache.struts2.jcr.wrapper;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.jcr.*;
import javax.jcr.lock.Lock;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.nodetype.NodeDefinition;
import javax.jcr.nodetype.NodeType;
import javax.jcr.version.Version;
import javax.jcr.version.VersionException;
import javax.jcr.version.VersionHistory;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

public class NodeWrapperImpl implements  NodeWrapper, Serializable {
  private static Logger logger = Logger.getLogger(NodeWrapperImpl.class);

  private Node delegee;
  protected WrapperFactory wrapperFactory;

  public NodeWrapperImpl(Node delegee,WrapperFactory wrapperFactory) {
    this.delegee = delegee;
    this.wrapperFactory = wrapperFactory;
  }

  public String getPath() throws RepositoryException {
    return delegee.getPath();
  }

  public String getName() throws RepositoryException {
    return delegee.getName();
  }

  public Item getAncestor(int depth) throws  AccessDeniedException, RepositoryException {
    return delegee.getAncestor(depth);
  }

  public Node getParent() throws  AccessDeniedException, RepositoryException {
    return wrapperFactory.createWrapped(delegee.getParent());
  }

  public int getDepth() throws RepositoryException {
    return delegee.getDepth();
  }

  public Session getSession() throws RepositoryException {
    return delegee.getSession();
  }

  public boolean isNode() {
    return delegee.isNode();
  }

  public boolean isNew() {
    return delegee.isNew();
  }

  public boolean isModified() {
    return delegee.isModified();
  }

  public boolean isSame(Item otherItem) throws RepositoryException {
    return delegee.isSame(otherItem);
  }

  public void accept(ItemVisitor visitor) throws RepositoryException {
     delegee.accept(visitor);
  }

  public void save() throws RepositoryException {
    delegee.save();
  }

  public void refresh(boolean keepChanges) throws RepositoryException {
    delegee.refresh(keepChanges);
  }

  public void remove() throws RepositoryException {
    delegee.remove();
  }

  public Node addNode(String relPath) throws RepositoryException{
    return wrapperFactory.createWrapped(delegee.addNode(relPath));
  }

  public Node addNode(String relPath, String primaryNodeTypeName) throws PathNotFoundException, NoSuchNodeTypeException, LockException, VersionException, ConstraintViolationException, RepositoryException {
    return delegee.addNode(relPath,primaryNodeTypeName);
  }

  public void orderBefore(String srcChildRelPath, String destChildRelPath) throws VersionException, ConstraintViolationException, ItemNotFoundException, LockException, RepositoryException {
     delegee.orderBefore(srcChildRelPath,destChildRelPath);
  }

  public Property setProperty(String name, Value value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,value);
  }

  public Property setProperty(String name, Value value, int type) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,value,type);
  }

  public Property setProperty(String name, Value[] values) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,values);
  }

  public Property setProperty(String name, Value[] values, int type) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,values,type);
  }

  public Property setProperty(String name, String[] values) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,values);
  }

  public Property setProperty(String name, String[] values, int type) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,values,type);
  }

  public Property setProperty(String name, String value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,value);
  }

  public Property setProperty(String name, String value, int type) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,value,type);
  }

  public Property setProperty(String name, InputStream value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,value);
  }

  public Property setProperty(String name, boolean value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,value);
  }

  public Property setProperty(String name, double value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,value);
  }

  public Property setProperty(String name, long value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,value);
  }

  public Property setProperty(String name, Calendar value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    return delegee.setProperty(name,value);
  }

  public Property setProperty(String name, Node value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
    if(value==null){
      return delegee.setProperty(name, (Value) null);
    }
    while(value instanceof NodeWrapperImpl){
      value=((NodeWrapperImpl)value).getDelegee();
    }
    return delegee.setProperty(name,value);
  }

  public Node getNode(String relPath) throws PathNotFoundException, RepositoryException {
    return wrapperFactory.createWrapped(delegee.getNode(relPath));
  }

  public NodeIterator getNodes() throws RepositoryException {
    return wrapperFactory.createWrapped(delegee.getNodes());    
  }

  public NodeIterator getNodes(String namePattern) throws RepositoryException {
    return wrapperFactory.createWrapped(delegee.getNodes(namePattern));
  }

  public Property getProperty(String relPath) throws PathNotFoundException, RepositoryException {
    return wrapperFactory.createWrapped(delegee.getProperty(relPath));
  }

  public PropertyIterator getProperties() throws RepositoryException {
    return delegee.getProperties();
  }

  public PropertyIterator getProperties(String namePattern) throws RepositoryException {
    return delegee.getProperties(namePattern);
  }

  public Item getPrimaryItem() throws ItemNotFoundException, RepositoryException {
    return delegee.getPrimaryItem();
  }

  public String getUUID() throws UnsupportedRepositoryOperationException, RepositoryException {
    return delegee.getUUID();
  }

  public int getIndex() throws RepositoryException {
    return delegee.getIndex();
  }

  public PropertyIterator getReferences() throws RepositoryException {
    return delegee.getReferences();
  }

  public boolean hasNode(String relPath) throws RepositoryException {
    return delegee.hasNode(relPath);
  }

  public boolean hasProperty(String relPath) throws RepositoryException {
    return delegee.hasProperty(relPath);
  }

  public boolean hasNodes() throws RepositoryException {
    return delegee.hasNodes();
  }

  public boolean hasProperties() throws RepositoryException {
    return delegee.hasProperties();
  }

  public NodeType getPrimaryNodeType() throws RepositoryException {
    return delegee.getPrimaryNodeType();
  }

  public NodeType[] getMixinNodeTypes() throws RepositoryException {
    return delegee.getMixinNodeTypes();
  }

  public boolean isNodeType(String nodeTypeName) throws RepositoryException {
    return delegee.isNodeType(nodeTypeName);
  }

  public void addMixin(String mixinName) throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
    delegee.addMixin(mixinName);
  }

  public void removeMixin(String mixinName) throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
    delegee.removeMixin(mixinName);
  }

  public boolean canAddMixin(String mixinName) throws NoSuchNodeTypeException, RepositoryException {
    return delegee.canAddMixin(mixinName);
  }

  public NodeDefinition getDefinition() throws RepositoryException {
    return delegee.getDefinition();
  }

  public Version checkin() throws VersionException, UnsupportedRepositoryOperationException, InvalidItemStateException, LockException, RepositoryException {
    return delegee.checkin();
  }

  public void checkout() throws UnsupportedRepositoryOperationException, LockException, RepositoryException {
    delegee.checkout();
  }

  public void doneMerge(Version version) throws VersionException, InvalidItemStateException, UnsupportedRepositoryOperationException, RepositoryException {
    delegee.doneMerge(version);
  }

  public void cancelMerge(Version version) throws VersionException, InvalidItemStateException, UnsupportedRepositoryOperationException, RepositoryException {
    delegee.cancelMerge(version);
  }

  public void update(String srcWorkspaceName) throws NoSuchWorkspaceException, AccessDeniedException, LockException, InvalidItemStateException, RepositoryException {
    delegee.update(srcWorkspaceName);
  }

  public NodeIterator merge(String srcWorkspace, boolean bestEffort) throws NoSuchWorkspaceException, AccessDeniedException, MergeException, LockException, InvalidItemStateException, RepositoryException {
    return delegee.merge(srcWorkspace,bestEffort);
  }

  public String getCorrespondingNodePath(String workspaceName) throws ItemNotFoundException, NoSuchWorkspaceException, AccessDeniedException, RepositoryException {
    return delegee.getCorrespondingNodePath(workspaceName);
  }

  public boolean isCheckedOut() throws RepositoryException {
    return delegee.isCheckedOut();
  }

  public void restore(String versionName, boolean removeExisting) throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException, RepositoryException {
    delegee.restore(versionName,removeExisting);
  }

  public void restore(Version version, boolean removeExisting) throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, RepositoryException {
    delegee.restore(version,removeExisting);
  }

  public void restore(Version version, String relPath, boolean removeExisting) throws PathNotFoundException, ItemExistsException, VersionException, ConstraintViolationException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException, RepositoryException {
    delegee.restore(version,relPath,removeExisting);
  }

  public void restoreByLabel(String versionLabel, boolean removeExisting) throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException, RepositoryException {
    delegee.restoreByLabel(versionLabel,removeExisting);
  }

  public VersionHistory getVersionHistory() throws UnsupportedRepositoryOperationException, RepositoryException {
    return wrapperFactory.createWrapped(delegee.getVersionHistory());
  }

  public Version getBaseVersion() throws UnsupportedRepositoryOperationException, RepositoryException {
    return delegee.getBaseVersion();
  }

  public Lock lock(boolean isDeep, boolean isSessionScoped) throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException, RepositoryException {
    return delegee.lock(isDeep,isSessionScoped);
  }

  public Lock getLock() throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, RepositoryException {
    return delegee.getLock();
  }

  public void unlock() throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException, RepositoryException {
    delegee.unlock();
  }

  public boolean holdsLock() throws RepositoryException {
    return delegee.holdsLock();
  }

  public boolean isLocked() throws RepositoryException {
    return delegee.isLocked();
  }

  void log(Throwable e) {
    logger.error(e);
  }

  public int size() {
    try {
      return (int) delegee.getProperties().getSize()-1;
    } catch (RepositoryException e) {
      log(e);
      return -1;
    }
  }

  public boolean isEmpty() {
    return size() < 2;
  }

  public boolean containsKey(Object key) {
    if (key == null) {
      return false;
    }
    try {
      return delegee.hasProperty(key.toString());
    } catch (RepositoryException e) {
      log(e);
      return false;
    }
  }

  public boolean containsValue(Object value) {
    return false;
  }

  public Object get(Object key) {
    if (key == null) {
      return null;
    }
    String delegeeName="delegee is null";
    try {
      delegeeName=delegee.getName();
      return PropertyUtils.getObjectByProperty(delegee.getProperty(key.toString()), wrapperFactory);
    } catch (Throwable e) {
      logger.error("Error: "+delegeeName+":"+key.toString());
      log(e);
    }
    return null;
  }

  public Object put(String key, Object value) {
    if (value == null) {
      Property property = null;
      try {
        property = delegee.getProperty(key);
        if (property != null) {
          property.remove();
        }
        return null;
      } catch (RepositoryException e) {
        throw new RuntimeException(e);
      }
    }
    if(StringUtils.isEmpty(key)){
      throw new RuntimeException("Empty property name");
    }
    if(value!=null && value.getClass().isArray() && ((Object[])value).length==1){
      value=((Object[])value)[0];
    }
    try {
      int type = PropertyUtils.getObjectType(value);
      value = PropertyUtils.generalizeObjectType(value, type);
      switch (type) {
        case PropertyType.DATE: {
          Calendar instance = Calendar.getInstance();
          instance.setTime((Date) value);
          return delegee.setProperty(key, instance);
        }
        case PropertyType.LONG: {
          return delegee.setProperty(key, (Long) value);
        }
        case PropertyType.DOUBLE: {
          return delegee.setProperty(key, (Double) value);
        }
        case PropertyType.BOOLEAN: {
          return delegee.setProperty(key, (Boolean) value);
        }
        case PropertyType.STRING: {
          return delegee.setProperty(key, value.toString());
        }
        case PropertyType.BINARY: {
          return delegee.setProperty(key, (InputStream) value);
        }
        case PropertyType.REFERENCE: {
          return delegee.setProperty(key,((NodeWrapperImpl)value).getDelegee());
        }
        default: {
          throw new RuntimeException("Unknown Object Type");
        }
      }
    } catch (RepositoryException e) {
      logger.error(e);
    }
    return null;
  }

  public Node getDelegee() {
    return delegee;
  }

  public Object remove(Object key) {
    try {
      Property property = delegee.getProperty(key.toString());
      Object objectByProperty = PropertyUtils.getObjectByProperty(property, wrapperFactory);
      delegee.setProperty(key.toString(), (Value) null);
      return objectByProperty;
    } catch (RepositoryException e) {
      log(e);
      return null;
    }
  }

  public void putAll(Map map) {
    Set<String> set = map.keySet();
    for (String key : set) {
      put(key, map.get(key));
    }
  }

  public void clear() {
    try {
      PropertyIterator properties = delegee.getProperties();
      while (properties.hasNext()) {
        Property property = properties.nextProperty();
        if(!"jcr:primaryType".equals(property.getName())){
          property.setValue((Value) null);
        }
      }
    } catch (RepositoryException e) {
      log(e);
    }
  }

  public Set keySet() {
    Set set = new HashSet();
    try {
      PropertyIterator properties = delegee.getProperties();
      while (properties.hasNext()) {
        Property property = properties.nextProperty();
        if(!"jcr:primaryType".equals(property.getName())){
          set.add(property.getName());
        }
      }
    } catch (RepositoryException e) {
      log(e);
    }
    return set;
  }

  public Collection values() {
    List list = new ArrayList();
    try {
      PropertyIterator properties = delegee.getProperties();
      while (properties.hasNext()) {
        Property property = properties.nextProperty();
        if(!"jcr:primaryType".equals(property.getName())){
          list.add(PropertyUtils.getObjectByProperty(property, wrapperFactory));
        }
      }
    } catch (RepositoryException e) {
      log(e);
    }
    return list;
  }


  public Set entrySet() {
    Set<Map.Entry<String, Object>> entries =  new HashSet<Entry<String, Object>>();
    try {
      PropertyIterator properties = delegee.getProperties();
      while(properties.hasNext()){
        final Property property = properties.nextProperty();
        if("jcr:primaryType".equals(property.getName())){
          continue;
        }
        final String key = property.getName();
        final Object value = PropertyUtils.getObjectByProperty(property,wrapperFactory);
        entries.add(new Entry<String, Object>() {
          public String getKey() {
            return key;
          }

          public Object getValue() {
            return value;
          }

          public Object setValue(Object aValue) {
            return put(key, aValue);
          }
        });
      }
    } catch (RepositoryException e) {
      log(e);
    }
    return entries;
  }


  public String toString() {
    try {
      return "["+getName()+"]";
    } catch (RepositoryException e) {
      logger.error(e);
      return "[error retrieving node name]";
    }
  }
}
